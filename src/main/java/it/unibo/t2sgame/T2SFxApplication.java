package it.unibo.t2sgame;

import it.unibo.t2sgame.view.impl.WindowJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * JavaFX application for T2SGame.
 */
public class T2SFxApplication extends Application {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        new WindowJavaFX().launch();
    }
}
