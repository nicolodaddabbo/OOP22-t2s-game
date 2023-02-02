package it.unibo.t2sgame.core.engine.impl;

import java.util.List;
import java.util.Optional;

import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.core.engine.api.GameLoop;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.view.api.GameScene;

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
    private GameLoop gameLoop = new SynchronizeGameLoop(new FpsLockedGameLoop(new BasicGameLoop(this)));

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

    @Override
    public GameScene getScene() {
        return this.view;
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
     * Initialize the engine before start to running
     */
    private void init() {
        // Setting to the view all the players input controller
        this.view.setInputControllers(this.getKeyboardInputController());
    }

}
