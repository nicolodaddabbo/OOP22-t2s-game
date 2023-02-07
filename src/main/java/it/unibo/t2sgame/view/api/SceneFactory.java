package it.unibo.t2sgame.view.api;

/**
 * interface that represents a SceneFactory.
 */
public interface SceneFactory {
    /**
     * method called to create a GameScene.
     * @param window where you need to create the GameScene.
     * @return the created GameScene
     */
    GameScene createGameScene(Window window);
    /**
     * method called to create a BaseScene representing a MenuScene.
     * @param window where you need to create the MenuScene
     * @return the created BaseScene
     */
    BaseScene createMenuScene(Window window);
    /**
     * method called to create a BaseScene representing a GameOverScene.
     * @param window where you need to create the GameOverScene
     * @param round round in which the player has died
     * @return the created BaseScene
     */
    BaseScene createGameOverScene(Window window, int round);
}
