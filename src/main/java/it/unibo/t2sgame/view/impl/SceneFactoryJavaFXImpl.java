package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.BaseScene;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.SceneFactory;
import javafx.stage.Stage;

public class SceneFactoryJavaFXImpl implements SceneFactory{

    private final Stage stage;

    public SceneFactoryJavaFXImpl(final Stage stage){
        this.stage = stage;
    }
    
    @Override
    public GameScene createGameScene() {
        return new GameSceneJavaFXImpl(stage);
    }
    
    public BaseScene createMenuScene(){
        return new MenuJavaFXImpl(stage);
    }
}
