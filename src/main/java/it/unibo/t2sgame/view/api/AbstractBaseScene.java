package it.unibo.t2sgame.view.api;
/**
 * abstract class that contains all the common implementation and variables for every BaseScene.
 */
public abstract class AbstractBaseScene implements BaseScene {
    protected final Window window;
    protected static final double BASEWIDTH = 1920;
    protected static final double BASEHEIGHT = 1080;
    protected static final double FONTSIZE = 30;
    protected static final double PADDING = 10;
    /**
     * construcor of an abstract game scene.
     * @param window in which the scene is going to be contained
     */
    protected AbstractBaseScene(final Window window) {
        this.window = window;
    }
}
