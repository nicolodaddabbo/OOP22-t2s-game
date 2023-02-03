package it.unibo.t2sgame.view.api;

public abstract class AbstractBaseScene implements BaseScene{

    protected final Window window;
    
    protected AbstractBaseScene(Window window) {
        this.window = window;
    }
}
