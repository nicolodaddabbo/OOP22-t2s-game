package it.unibo.t2sgame.view.api;
/**
 * abstract class that contains all the common implementation and variables for every BaseScene.
 */
public abstract class AbstractBaseScene implements BaseScene {
    private final Window window;
    private static final double BASEWIDTH = 1920;
    private static final double BASEHEIGHT = 1080;
    private static final double FONTSIZE = 30;
    private static final double PADDING = 10;
    /**
     * construcor of an abstract game scene.
     * @param window in which the scene is going to be contained
     */
    protected AbstractBaseScene(final Window window) {
        this.window = window;
    }
    /**
     * getter for the Window.
     * @return the window
     */
    public Window getWindow() {
        return this.window;
    }
    /**
     * getter for static value BASEWIDTH.
     * @return the base width
     */
    public static double getBaseWidth() {
        return BASEWIDTH;
    }
    /**
     * getter for static value BASEHEIGHT.
     * @return the base height
     */
    public static double getBaseHeight() {
        return BASEHEIGHT;
    }
    /**
     * getter for static value FONTSIZE.
     * @return the font size
     */
    public static double getFontSize() {
        return FONTSIZE;
    }
    /**
     * getter for static value PADDING.
     * @return the padding
     */
    public static double getPadding() {
        return PADDING;
    }
}
