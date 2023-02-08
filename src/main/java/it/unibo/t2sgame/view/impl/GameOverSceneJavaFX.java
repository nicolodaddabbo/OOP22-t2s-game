package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.AbstractBaseScene;
import it.unibo.t2sgame.view.api.Window;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
/**
 * class representing a GameOverScene in JavaFX.
 */
public class GameOverSceneJavaFX extends AbstractBaseScene {
    private final int round;
    private final Stage stage;
    /**
     * @param stage in which the scene is to be rendered
     * @param window in which the stage is contained 
     * @param round in which the user lost
     */
    protected GameOverSceneJavaFX(final Stage stage, final Window window, final int round) {
        super(window);
        this.round = round;
        this.stage = stage;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        var root = new GridPane();
        var gameOverLabel = new Label("GAME OVER\nYOU DIED AT ROUND " + this.round);
        var screenBounds = Screen.getPrimary().getBounds();
        var dpiW = screenBounds.getWidth() / AbstractBaseScene.BASEWIDTH;
        var dpiH = screenBounds.getHeight() / AbstractBaseScene.BASEHEIGHT;
        gameOverLabel.setTextFill(Color.WHITE);
        gameOverLabel.setFont(Font.font(null, FontWeight.BOLD, AbstractBaseScene.FONTSIZE * dpiW));
        gameOverLabel.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHalignment(gameOverLabel, HPos.CENTER);
        root.add(gameOverLabel, 1, 1);
        root.setMinSize(AbstractBaseScene.BASEWIDTH * dpiW / 2, AbstractBaseScene.BASEHEIGHT * dpiH / 2);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color:#000");
        var gameOverScene = new Scene(root);
        this.stage.centerOnScreen();
        this.stage.setScene(gameOverScene);
        this.stage.show();
    }
}
