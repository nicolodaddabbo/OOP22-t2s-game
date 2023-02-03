package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.AbstractBaseScene;
import it.unibo.t2sgame.view.api.Window;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverSceneJavaFX extends AbstractBaseScene{

    private final int round;
    private final Stage stage;
    protected GameOverSceneJavaFX(Stage stage, Window window, int round) {
        super(window);
        this.round = round;
        this.stage = stage;
    }

    @Override
    public void initialize() {
        var root = new Pane();
        var text = new Text("GAME OVER AL ROUND " + round);
        root.getChildren().add(text);
        var gameOverScene = new Scene(root, this.stage.getWidth(), this.stage.getHeight(), Color.BLACK);

        this.stage.setScene(gameOverScene);
        this.stage.show();
    }
    
}
