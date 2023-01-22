package it.unibo.t2sgame.view.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.core.api.GameEngine;
import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.HealthComponent;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.GraphicComponent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSceneJavaFXImpl implements GameScene{

    private Group root;
    private Scene scene;
    private Canvas canvas;
    private Canvas healthCanvas;
    private KeyboardInputController keyInController;
    private Game game;
    private GraphicsContext gContext, healthContext;
    private GameEngine gameEngine;
    private GraphicJavaFXImpl graphic;
    private Map<String, Image> cachedSprites;
    @Override
    public void initialize() {
        Stage stage = new Stage();
        this.root = new Group();
        this.canvas = new Canvas(1200, 600);
        this.gContext = this.canvas.getGraphicsContext2D();
        this.healthCanvas = new Canvas(1200, 50);
        this.healthCanvas.toFront();
        this.graphic = new GraphicJavaFXImpl(this.gContext);
        this.scene = new Scene(this.root, 1200, 600, Color.BLACK);
        this.scene.setOnKeyPressed(event -> keyInController.notifyKeyPressed(event.getCode().getCode()));
        this.scene.setOnKeyReleased(event -> keyInController.notifyKeyReleased(event.getCode().getCode()));

        this.healthCanvas = new Canvas(300, 50);
        this.healthContext = this.healthCanvas.getGraphicsContext2D();
        this.root.getChildren().add(this.healthCanvas);
        this.root.getChildren().add(this.canvas);
        this.canvas.toBack();
        stage.setResizable(false);
        stage.setScene(this.scene);
        stage.setTitle("T2S-game");
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
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
                .ifPresent(gc -> this.draw(gc, entity)));     
           
            healthContext.clearRect(0, 0, healthCanvas.getWidth(), healthCanvas.getHeight());
            var health = (this.game.getWorld().getPlayers().get(0).getComponent(HealthComponent.class).get()).getHealth();
            Stream.iterate(0, i -> i + 1)
                .limit(health)
                .forEach(n -> this.healthContext.drawImage(cachedSprites.get("full_heart"), 50*n, 0, 40, 40));
        });
    }

    private void draw(GraphicComponent gc, Entity entity){
        gc.setGraphics(graphic);
        gc.update(entity);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        /**
         * This is a code only used to test the correct functionlaity it will be changed
         */
        this.game.getWorld().getPlayers().get(0)
            .getComponent(InputComponent.class)
            .ifPresent(c -> this.keyInController = (KeyboardInputController)(c).getInputController());
    }

    @Override
    public void setKeyboardInputController(KeyboardInputController keyInController) {
        this.keyInController = keyInController;
    }

    @Override
    public void setEngine(GameEngine gameEngine) {
        this.gameEngine =  gameEngine;
    }
}
