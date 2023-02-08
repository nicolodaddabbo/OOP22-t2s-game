package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.AbstractWindow;
import it.unibo.t2sgame.view.api.SceneFactory;
import javafx.stage.Stage;
/**
 * class representing a Window using JavaFX.
 */
public class WindowJavaFX extends AbstractWindow {
    private final Stage stage;
    private final SceneFactory sceneFactory;
    /**
     * constructor for WindowJavaFX.
     */
    public WindowJavaFX() {
        this.stage = new Stage();
        this.sceneFactory = new SceneFactoryJavaFXImpl(stage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }
}
