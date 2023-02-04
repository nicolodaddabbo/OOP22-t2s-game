package it.unibo.t2sgame.view.api;

/**
 * Interface that represents the concept of a Window (a container of scenes)
 */
public interface Window {
    /**
     * method called to launch the Window
     */
    void launch();
    /**
     * method called to create a GameScene in the Window that called this method
     * @return the GameScene
     */
    GameScene createGameScene();
    /**
     * method called to create a BaseScene that represents a Menu in the Window that called this method
     * @return the MenuScene
     */
    BaseScene createMenuScene();
    /**
     * method called to create a BaseScene that represents a GameOverScene in the Window that called this method
     * @return the GameScene
     */
    BaseScene createGameOverScene(int round);
}
