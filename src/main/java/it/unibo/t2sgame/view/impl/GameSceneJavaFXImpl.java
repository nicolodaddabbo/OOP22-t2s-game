package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.core.api.Game;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.GameScene;
import it.unibo.t2sgame.view.api.Graphic;
import it.unibo.t2sgame.view.api.GraphicComponent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSceneJavaFXImpl extends Application implements GameScene{

    private Canvas root;
    private Scene scene;
    private KeyboardInputController keyInController;
    private Game game;
    private GraphicsContext gContext;

    @Override
    public void initialize() {
        Application.launch(" ");
    } 

    @Override
    public void start(Stage stage) throws Exception {
        this.root = new Canvas();
        this.gContext = this.root.getGraphicsContext2D();
        this.scene = new Scene(new StackPane(this.root), 500, 500);
        this.scene.setFill(Color.BLACK);
        this.scene.setOnKeyPressed(event -> keyInController.notifyKeyPressed(event.getCode().getCode()));
        this.scene.setOnKeyReleased(event -> keyInController.notifyKeyReleased(event.getCode().getCode()));

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
