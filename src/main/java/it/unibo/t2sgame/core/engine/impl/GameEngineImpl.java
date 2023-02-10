package it.unibo.t2sgame.core.engine.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.ecs.api.Component;
import it.unibo.t2sgame.game.ecs.impl.GraphicComponent;
import it.unibo.t2sgame.game.ecs.impl.InputComponent;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;

/**
 * This class represents the GameEngine's base implementation.
 * The engine will run on a different thread respect from the one who call the
 * run method.
 * This thread will close when stop method will called.
 */
public class GameEngineImpl implements GameEngine {
    /*
     * The game which is hosted by the engine.
     */
    private final Game game;
    /*
     * The view where the engine will render the game.
     */
    private Optional<GameScene> view;
    /*
     * Represents the "Game Loop", to start the loop use gameLoop.run().
     */
    private final Runnable gameLoop = new RunnableGameLoop(this);
    /*
     * The thread where the engine works.
     */
    private Thread thread;
    /*
     * Represents the running state of the engine.
     */
    private boolean state;

    /**
     * Create a GameEngine's istance based {@link game} hosted.
     * 
     * @param game the game hosted by the engine.
     */
    public GameEngineImpl(final Game game) {
        this.view = Optional.empty();
        this.game = game;
    }

     /**
     * Create a GameEngine's copy.
     * 
     * @param engine the engine to copy.
     */
    public GameEngineImpl(final GameEngine engine) {
        this.view = engine.getScene();
        this.game = engine.getGame();
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (this.isRunning()) {
            throw new IllegalStateException();
        }
        this.init();
        this.thread = new Thread(this.gameLoop);
        this.thread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        if (!this.isRunning()) {
            throw new IllegalStateException();
        }
        this.state = false;
        this.thread.interrupt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return this.state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGraphics(final Graphic g) {
        final var clazz = GraphicComponent.class;
        this.getComponents(clazz).forEach(gc -> {
            gc.setGraphics(g);
            gc.update();
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Component> List<T> getComponents(final Class<T> clazz) {
        return this.game.getWorld().getEntities().stream()
                .flatMap(e -> e.getComponent(clazz).stream())
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<GameScene> getScene() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScene(final GameScene scene) {
        this.view = Optional.of(scene);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game getGame() {
        return this.game;
    }

    private List<KeyboardInputController> getKeyboardInputController() {
        return this.game.getWorld().getPlayers().stream()
                .flatMap(p -> p.getComponent(InputComponent.class).stream())
                // Map each InputComponent with its InputController
                .map(InputComponent::getInputController)
                // Cast to KeyboardInputController
                .filter(KeyboardInputController.class::isInstance)
                .map(KeyboardInputController.class::cast)
                // Collect the input controllers in a list
                .collect(Collectors.toUnmodifiableList());
    }

    private void init() {
        // Set the runnig state on true.
        this.state = true;
        // Setting to the view all the players input controller
        this.view.ifPresent(s -> s.setInputControllers(this.getKeyboardInputController()));
    }

}
