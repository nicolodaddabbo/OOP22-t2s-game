package it.unibo.t2sgame.view.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.t2sgame.core.components.impl.GraphicComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.view.api.Graphic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GraphicJavaFXImpl implements Graphic {

    private final GraphicsContext graphicContext;
    private Map<String, Image> cachedSprites;
    private double entityX;
    private double entityY;
    private double dpiW;
    private double dpiH;
    private double width;
    private double height;

    GraphicJavaFXImpl(GraphicsContext gc, double dpiW, double dpiH) {
        this.graphicContext = gc;
        this.dpiW = dpiW;
        this.dpiH = dpiH;
        storeSprites();
    }

    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("player", new Image(new FileInputStream("src/main/resources/sprites/ghost.gif")));
            this.cachedSprites.put("enemy", new Image(new FileInputStream("src/main/resources/sprites/blob.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawFromSprite(Entity entity, String spirteName) {
        entity.getComponent(GraphicComponent.class).ifPresent(component -> {
            entityX = entity.getPosition().getX() * this.dpiW;
            entityY = entity.getPosition().getY() * this.dpiH;
            width = component.getWidth() * this.dpiW;
            height = component.getHeight() * this.dpiH;
            this.graphicContext.drawImage(
                    cachedSprites.get(spirteName), 
                    entityX - width / 2, 
                    entityY - height / 2,
                    width,
                    height
            );
        });

    }

    @Override
    public void drawRectangle(Entity entity) {
        entity.getComponent(GraphicComponent.class).ifPresent(component -> {
            entityX = entity.getPosition().getX() * this.dpiW;
            entityY = entity.getPosition().getY() * this.dpiH;
            width = component.getWidth() * this.dpiW;
            height = component.getHeight() * this.dpiH;
            graphicContext.setFill(Color.WHITE);
            this.graphicContext.fillRect(
                    entityX - width / 2, 
                    entityY - height / 2,
                    width,
                    height
            );
        });
    }

    @Override
    public void drawCircle(Entity entity) {
        entity.getComponent(GraphicComponent.class).ifPresent(component -> {
            entityX = entity.getPosition().getX() * this.dpiW;
            entityY = entity.getPosition().getY() * this.dpiH;
            width = component.getWidth() * this.dpiW;
            height = component.getHeight() * this.dpiH;
            graphicContext.setFill(Color.WHITE);
            this.graphicContext.fillOval(
                    entityX - width / 2, 
                    entityY - height / 2,
                    width,
                    height
            );
        });
    }
}
