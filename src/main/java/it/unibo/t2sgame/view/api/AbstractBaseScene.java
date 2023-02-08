package it.unibo.t2sgame.view.api;

public abstract class AbstractBaseScene implements BaseScene {
    protected final Window window;
    protected static final double BASEWIDTH = 1920;
    protected static final double BASEHEIGHT = 1080;
    protected static final double FONTSIZE = 30;

    protected AbstractBaseScene(final Window window) {
        this.window = window;
    }
}
