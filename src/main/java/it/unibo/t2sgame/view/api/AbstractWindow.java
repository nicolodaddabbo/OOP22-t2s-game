package it.unibo.t2sgame.view.api;

public abstract class AbstractWindow implements Window {
    protected SceneFactory sceneFactory;

    @Override
    public void launch() {
        var menu = this.createMenuScene(); 
        menu.initialize();
    }

    @Override
    public GameScene createGameScene() {
        return this.getSceneFactory().createGameScene(this);
    }

    @Override
    public BaseScene createMenuScene() {
        return this.getSceneFactory().createMenuScene(this);
    }

    @Override
    public BaseScene createGameOverScene(final int round) { 
        return this.getSceneFactory().createGameOverScene(this, round);
    }

    protected abstract SceneFactory getSceneFactory();
}
