package it.unibo.t2sgame.view.api;

public interface SceneFactory {
    /**
     * 
     * @return a created Scene
     */
    GameScene createScene();

    /**
     * 
     * @return the Graphic that the application is going to use
     */
    Graphic createGraphic();
}
