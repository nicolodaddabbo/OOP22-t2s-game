package it.unibo.t2sgame.view.api;

public interface SceneFactoy {
    
    /**
     * 
     * @return a created Scene
     */
    Scene createScene();

    /**
     * 
     * @return the Graphic that the application is going to use
     */
    Graphic createGraphic();
}
