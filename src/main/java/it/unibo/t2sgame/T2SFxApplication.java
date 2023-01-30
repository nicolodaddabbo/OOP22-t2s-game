package it.unibo.t2sgame;

import it.unibo.t2sgame.view.impl.WindowJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class T2SFxApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        new WindowJavaFX(primaryStage).launch();
    }
}
