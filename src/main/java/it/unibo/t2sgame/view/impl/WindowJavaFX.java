package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.AbstractWindow;
import it.unibo.t2sgame.view.api.SceneFactory;
import javafx.stage.Stage;

public class WindowJavaFX extends AbstractWindow {
    private final Stage stage;

    public WindowJavaFX() {
        this.stage = new Stage();
        this.sceneFactory = new SceneFactoryJavaFXImpl(stage);
    }

    @Override
    protected SceneFactory getSceneFactory() {
        return this.sceneFactory;
    }
}
