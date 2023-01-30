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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSceneJavaFXImpl implements GameScene {
    private Canvas canvas;
    private KeyboardInputController keyInController;
    private Game game;
    private GraphicsContext gContext;
    private GraphicJavaFXImpl graphic;
    private Map<String, Image> cachedSprites;
    private final Stage stage;
    private GameEngine gameEngine;
    
    public GameSceneJavaFXImpl(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize() {
        var root = new Group();
        this.canvas = new Canvas(1200, 800);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.graphic = new GraphicJavaFXImpl(this.gContext);
        var scene = new Scene(root, 1200, 800, Color.BLACK);
        scene.setOnKeyPressed(event -> keyInController.notifyKeyPressed(event.getCode().getCode()));
        scene.setOnKeyReleased(event -> keyInController.notifyKeyReleased(event.getCode().getCode()));
        root.getChildren().add(this.canvas);
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
            this.cachedSprites.put("full_heart", new Image(new FileInputStream("src/main/resources/heart_darker.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render() {    
        Platform.runLater(() -> {
            gContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.game.getWorld().getEntities().forEach(entity -> entity
                .getComponent(GraphicComponent.class)
                .ifPresent(gc -> {
                    gc.setGraphics(graphic);
                    gc.update();
                }));     
           
            var health = (game.getWorld().getPlayers().get(0).getComponent(HealthComponent.class).get()).getHealth();
            Stream.iterate(0, i -> i + 1)
                .limit(health)
                .forEach(n -> this.gContext.drawImage(cachedSprites.get("full_heart"), 50*n, 0, 40, 40));
        });
    }
    
    @Override
    public void setEngine(GameEngine gameEngine) {
        this.gameEngine =  gameEngine;
    }

    public void setGame(Game game){
        this.game = game;

        /**
         * This code is here just for testing purposes
         */
        this.game.getWorld().getPlayers().get(0)
            .getComponent(InputComponent.class)
            .ifPresent(c -> this.keyInController = (KeyboardInputController)(c).getInputController());
    }

    public Graphic getGraphic(){
        return this.graphic;
    }

    public void setInputController(KeyboardInputController keyInController){
        this.keyInController = keyInController;
    }
}
