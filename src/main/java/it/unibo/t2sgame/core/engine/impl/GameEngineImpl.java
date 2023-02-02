package it.unibo.t2sgame.core.engine.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.view.api.GameScene;

public class GameEngineImpl implements GameEngine {
    /*
     * This long indicates the perod of updating the states systems in nanoseconds
     */
    private static final long NS_FRAME_PERIOD = (long) (7 * 1E6);
    /*
     * This long indicates the perod of updating the states systems in nanoseconds
     */
    private static final long NS_UPDATE_PERDIOD = (long) (7 * 1E6);
    /*
     * The game which is hosted by the engine
     */
    private final Game game;
    /*
     * The view where the engine will render the game
     */
    private final GameScene view;
    /* Usefull to synchronize states systems and to wait for next frame */
    private final StopWatch timer = new StopWatch().start();
    /* Represent the "gap" between game time and real time */
    private long lag = 0;

    /**
     * Create a GameEngine's istance based on {@link scene} and {@link game}
     * 
     * @param scene the scene which is delegated to render by the game engine
     * @param game  the game hosted by the engine
     */
    public GameEngineImpl(GameScene scene, Game game) {
        this.view = scene;
        this.game = game;
    }

    @Override
    public void run() {
        this.init();
        while (!this.game.isOver()) {
            this.lag = this.lag + this.timer.getElapsedNanos();
            this.timer.restart();
            this.updateComponentBy(InputComponent.class);
            /**
             * This loop substitutes the usage of a delta time step
             * variabile to procede the game time.
             * Using a delta time causes a non deterministic game
             * due to the number of update's calls which which
             * would be different based on the cpu / gpu "speed" of
             * the computer that is running the engine.
             */
            while (!this.isSync()) {
                this.updateGame();
                this.lag = this.lag - NS_UPDATE_PERDIOD;
            }
            this.view.render();
            this.waitForNextFrame();
        }
    }

    @Override
    public Game getGame() {
        return this.game;
    }

    @Override
    public <T extends Component> void updateComponentBy(Class<T> clazz) {
        this.getComponents(clazz).forEach(Component::update);
    }

    @Override
    public <T extends Component> void updateComponentByConcurrent(Class<T> clazz) {
        this.getComponents(clazz).stream().parallel().forEach(Component::update);
    }

    @Override
    public <T extends Component> List<T> getComponents(Class<T> clazz) {
        return this.game.getWorld().getEntities().stream()
                .map(e -> e.getComponent(clazz))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    private void updateGame() {
        this.updateComponentByConcurrent(PhysicsComponent.class);
        this.updateComponentByConcurrent(CollisionComponent.class);
        // Update the game logics and check events
        this.game.update();
    }

    /*
     * Getting the input controllers of the players
     */
    private List<KeyboardInputController> getKeyboardInputController() {
        return this.game.getWorld().getPlayers().stream()
                // Mapping every player with its input component
                .map(p -> p.getComponent(InputComponent.class))
                .filter(Optional::isPresent)
                .map(Optional::get)
                // Map each InputComponent with its InputController
                .map(InputComponent::getInputController)
                // Cast to KeyboardInputController
                .filter(KeyboardInputController.class::isInstance)
                .map(KeyboardInputController.class::cast)
                // Collect the input controllers in a list
                .toList();
    }

    /*
     * Check if the "game time" is synchronized with the "real time"
     */
    private boolean isSync() {
        return this.lag < NS_UPDATE_PERDIOD;
    }

    /*
     * Initialize the engine before start to running
     */
    private void init() {
        this.view.setInputControllers(this.getKeyboardInputController());
    }

    /**
     * A waiter function usefull to optimize performance.
     * As soon as the thread has told to scene to render, it sleeps
     * for a time which is equal to the frame period procession.
     * This will cause an fps lock during the game loop.
     */
    private void waitForNextFrame() {
        var timeToSleep = NS_FRAME_PERIOD - this.timer.getElapsedNanos();
        if (timeToSleep > 0) {
            try {
                // Sleeping time has to be converted in milliseconds
                Thread.sleep((long) ((timeToSleep) / 1E6));
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

}
