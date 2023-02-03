package it.unibo.t2sgame.view.api;

public interface Window {
    void launch();
    /**
     * 
     * @return a created Scene
     */
    GameScene createGameScene();

    BaseScene createMenuScene();
}
