package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.BaseScene;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.SceneFactory;
import it.unibo.t2sgame.view.api.Window;
import javafx.stage.Stage;
/**
 * class representing the implementation of a SceneFactory for a JavaFX scene.
 */
public class SceneFactoryJavaFXImpl implements SceneFactory {
    private final Stage stage;
    /**
     * constructor for SceneFactoryJavaFX.
     * @param stage the stage in which the Scene is to be rendered
     */
    public SceneFactoryJavaFXImpl(final Stage stage) {
        this.stage = new Stage(stage.getStyle());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameScene createGameScene(final Window window) {
        return new GameSceneJavaFXImpl(stage, window);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createMenuScene(final Window window) {
        return new MenuJavaFXImpl(stage, window);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createGameOverScene(final Window window, final int round) {
        return new GameOverSceneJavaFX(stage, window, round);
    }
}
