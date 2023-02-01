package it.unibo.t2sgame.view.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import it.unibo.t2sgame.core.components.impl.GraphicComponent;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.engine.api.GameEngine;
import it.unibo.t2sgame.game.Game;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;
import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameSceneJavaFXImpl implements GameScene {
    private Canvas canvas;
    private KeyboardInputController keyInController;
    private GraphicsContext gContext;
    private GraphicJavaFXImpl graphic;
    private Map<String, Image> cachedSprites;
    private final Stage stage;
    private GameEngine gameEngine;
    private Text round = new Text();
    private double dpiW;
    private double dpiH;
    private static final double BASEWIDTH = 1920;
    private static final double BASEHEIGHT = 1080;

    public GameSceneJavaFXImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize() {
        var root = new Group();
        var map = this.gameEngine.getGame().getWorld().getMap();
        var screenBounds = Screen.getPrimary().getBounds();
        /* takes the width and height of the primary screen and proportion it on the static sizes*/
        this.dpiW = screenBounds.getWidth() / GameSceneJavaFXImpl.BASEWIDTH; 
        this.dpiH = screenBounds.getHeight() / GameSceneJavaFXImpl.BASEHEIGHT; 
        var proportionedWidth = map.getWidth() * this.dpiW;
        var proportionedHeight = map.getHeight() * this.dpiH;
        var scene = new Scene(root, proportionedWidth, proportionedHeight, Color.BLACK);
        /*initializing all text settings settings */
        this.round.setText("");
        this.round.setFont(Font.font(null, FontWeight.BOLD, 30*this.dpiW));
        this.round.setTextOrigin(VPos.BOTTOM);
        this.round.setTextAlignment(TextAlignment.CENTER);
        this.round.setY(proportionedHeight);
        this.round.setStroke(Color.WHITE);
        root.getChildren().add(this.round);
        this.canvas = new Canvas(proportionedWidth, proportionedHeight);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.graphic = new GraphicJavaFXImpl(this.gContext, this.dpiW, this.dpiH);
        scene.setOnKeyPressed(event -> keyInController.notifyKeyPressed(event.getCode().getCode()));
        scene.setOnKeyReleased(event -> keyInController.notifyKeyReleased(event.getCode().getCode()));
        root.getChildren().add(this.canvas);
        root.getChildren().get(root.getChildren().indexOf(this.round)).toFront();
        stage.setResizable(false);
        stage.setScene(scene);  
        stage.centerOnScreen();
        stage.sizeToScene();
        storeSprites();
        stage.show();
    } 

    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("full_heart", new Image(new FileInputStream("src/main/resources/sprites/heart_darker.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        /**
         * This code is here just for testing purposes
         */
        this.getGame().getWorld().getPlayers().get(0)
            .getComponent(InputComponent.class)
            .ifPresent(c -> this.keyInController = (KeyboardInputController)(c).getInputController()); 

        Platform.runLater(() -> {
            gContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.gameEngine.getComponents(GraphicComponent.class).forEach(c -> {
                c.setGraphics(this.graphic);
                c.update();
            });

            //this.gameEngine.updateComponentBy(GraphicComponent.class); 
            this.getGame().getWorld().getPlayers().get(0).getComponent(HealthComponent.class).ifPresent(c -> {
                Stream.iterate(0, i -> i + 1)
                    .limit(c.getHealth())
                    .forEach(n -> this.gContext.drawImage(cachedSprites.get("full_heart"), 50*this.dpiW*n, 0, 40*this.dpiW, 40*this.dpiW));
            });

            this.round.setText("Round " + this.gameEngine.getGame().getState().getRound());
        });
    }
    
    @Override
    public void setEngine(GameEngine gameEngine) {
        this.gameEngine =  gameEngine;
    }

    public Game getGame(){
        return this.gameEngine.getGame();
    }

    public Graphic getGraphic(){
        return this.graphic;
    }

    public void setInputController(KeyboardInputController keyInController){
        this.keyInController = keyInController;
    }
}
