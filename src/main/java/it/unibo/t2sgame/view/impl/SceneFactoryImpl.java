package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.SceneFactory;

public class SceneFactoryImpl implements SceneFactory{

    @Override
    public GameScene createJavaFXScene() {
        return new GameSceneJavaFXImpl();
    }
    
}
