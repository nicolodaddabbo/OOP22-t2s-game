package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.GraphicComponent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSceneJavaFXImpl extends Application implements GameScene{

    private Group root;
    private Scene scene;
    private Canvas canvas;
    private KeyboardInputController keyInController;
    private Game game;
    private GraphicsContext gContext;

    @Override
    public void initialize() {
        Application.launch(GameSceneJavaFXImpl.class, " ");
    } 

    @Override
    public void init() throws Exception{
        this.root = new Group();
        this.canvas = new Canvas(500, 500);
        this.gContext = this.canvas.getGraphicsContext2D();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.scene = new Scene(this.root, 500, 500, Color.BLACK);
        this.scene.setOnKeyPressed(event -> keyInController.notifyKeyPressed(event.getCode().getCode()));
        this.scene.setOnKeyReleased(event -> keyInController.notifyKeyReleased(event.getCode().getCode()));

        this.root.getChildren().add(this.canvas);
        stage.setScene(this.scene);
        stage.setTitle("T2S-game");
        stage.show();
    }

    @Override
    public void render() {
        game.getWorld().getEntities().forEach(entity -> entity
            .getComponent(GraphicComponent.class)
            .ifPresent(gc -> this.draw((GraphicComponent) gc, entity)));
    }

    private void draw(GraphicComponent gc, Entity entity){
        gc.setGraphics(new GraphicJavaFXImpl(this.gContext));
        gc.update(entity);
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void setKeyboardInputController(KeyboardInputController keyInController) {
        this.keyInController = keyInController;
    }
}
