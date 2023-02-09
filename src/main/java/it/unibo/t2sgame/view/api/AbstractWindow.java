package it.unibo.t2sgame.view.api;
/**
 * abstract class that represent all the common implementation of a Window.
 */
public abstract class AbstractWindow implements Window {
    /**
     * {@inheritDoc}
     */
    @Override
    public void launch() {
        final var menu = this.createMenuScene(); 
        menu.initialize();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public GameScene createGameScene() {
        return this.getSceneFactory().createGameScene(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createMenuScene() {
        return this.getSceneFactory().createMenuScene(this);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createGameOverScene(final int round) { 
        return this.getSceneFactory().createGameOverScene(this, round);
    }
    /**
     * @return the SceneFactory that is going to be used here
     */
    protected abstract SceneFactory getSceneFactory();
}
