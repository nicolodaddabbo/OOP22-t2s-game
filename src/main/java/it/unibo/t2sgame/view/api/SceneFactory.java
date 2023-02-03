package it.unibo.t2sgame.view.api;

public interface SceneFactory {
    /**
     * 
     * @return a created Scene
     */
    GameScene createGameScene(Window window);

    BaseScene createMenuScene(Window window);
}
