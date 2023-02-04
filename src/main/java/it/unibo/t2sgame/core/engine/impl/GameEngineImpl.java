package it.unibo.t2sgame.core.engine.impl;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.impl.GraphicComponent;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.engine.api.GameLoop;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;

public class GameEngineImpl implements GameEngine {
    /*
     * The game which is hosted by the engine
     */
    private final Game game;
    /*
     * The view where the engine will render the game
     */
    private final GameScene view;
    /*
     * The gameLoop istance, delegating to it the handling of game loop body
     */
    private GameLoop gameLoop = new FpsCounterGameLoop(
            new SynchronizeGameLoop(new FpsLockedGameLoop(new ConcurrentGameLoop(this))));

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
            this.gameLoop.processInput();
            this.gameLoop.updateGame();
            this.gameLoop.render();
        }
        // Render Game over
        this.getScene().gameOver();
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
                .flatMap(e -> e.getComponent(clazz).stream())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public GameScene getScene() {
        return this.view;
    }

    @Override
    public void updateGraphics(Graphic g) {
        this.getComponents(GraphicComponent.class).stream()
                .peek(gc -> gc.setGraphics(g))
                .forEach(GraphicComponent::update);
    }

    /*
     * Getting the input controllers of the players
     */
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

    /*
     * Initialize the engine before start to running
     */
    private void init() {
        // Setting to the view all the players input controller
        this.view.setInputControllers(this.getKeyboardInputController());
    }

}
