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
import it.unibo.t2sgame.game.logics.api.GameMap;
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
    private double width, height;
    private double dpi;
    private GameMap map;
    private Text round = new Text();

    public GameSceneJavaFXImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize() {
        var root = new Group();
        this.map = this.gameEngine.getGame().getMap();
        this.dpi = Screen.getPrimary().getDpi(); 
        this.width = this.map.getWidth() / 100 * this.dpi;
        this.height = this.map.getHeight() / 100 * this.dpi;
        var scene = new Scene(root, this.width, this.height, Color.BLACK);
        this.round.setText("\n");
        this.round.setFont(Font.font(null, FontWeight.BOLD, 0.3*this.dpi));
        this.round.setTextOrigin(VPos.BOTTOM);
        this.round.setTextAlignment(TextAlignment.CENTER);
        this.round.setY(this.height);
        this.round.setStroke(Color.WHITE);
        root.getChildren().add(this.round);
        this.canvas = new Canvas(this.width, this.height);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.graphic = new GraphicJavaFXImpl(this.gContext, this.dpi);
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
            this.gameEngine.getComponents(GraphicComponent.class).forEach(c -> c.setGraphics(this.graphic));
            this.gameEngine.updateComponentBy(GraphicComponent.class);   
           
            var health = (this.getGame().getWorld().getPlayers().get(0).getComponent(HealthComponent.class).get()).getHealth();
            Stream.iterate(0, i -> i + 1)
                .limit(health)
                .forEach(n -> this.gContext.drawImage(cachedSprites.get("full_heart"), 0.5*dpi*n, 0, 0.4*dpi, 0.4*dpi));

            this.round.setText("Round " + String.valueOf(this.gameEngine.getGame().getState().getRound()));
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
