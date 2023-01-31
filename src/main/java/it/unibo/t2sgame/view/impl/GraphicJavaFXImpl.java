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

public class GraphicJavaFXImpl implements Graphic{

    private final GraphicsContext graphicContext;
    private Map<String, Image> cachedSprites;
    private double entityX;
    private double entityY;
    private double dpi;
    private double width;
    private double height;

    GraphicJavaFXImpl(GraphicsContext gc, double dpi){
        this.graphicContext = gc;
        this.dpi = dpi;
        storeSprites();
    }
    
    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("player", new Image(new FileInputStream("src/main/resources/sprites/ghost.gif")));
            this.cachedSprites.put("enemy", new Image(new FileInputStream("src/main/resources/sprites/enemy.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawFromSprite(Entity entity, String spirteName) {
        entityX = entity.getPosition().getX();
        entityY = entity.getPosition().getY();
        width = entity.getComponent(GraphicComponent.class).get().getWidth() / 100 * this.dpi;
        height = entity.getComponent(GraphicComponent.class).get().getHeight() / 100 * this.dpi;
        this.graphicContext.drawImage(cachedSprites.get(spirteName), entityX - width / 2, entityY - height / 2, width, height);  
    }

    @Override
    public void drawRectangle(Entity entity) {
        entityX = entity.getPosition().getX();
        entityY = entity.getPosition().getY();
        width = entity.getComponent(GraphicComponent.class).get().getWidth() / 100 * this.dpi;
        height = entity.getComponent(GraphicComponent.class).get().getHeight() / 100 * this.dpi;
        graphicContext.setFill(Color.WHITE);
        graphicContext.fillRect(entityX, entityX, 60, 80);
    }

    @Override
    public void drawCircle(Entity entity) {
        entityX = entity.getPosition().getX();
        entityY = entity.getPosition().getY();
        width = entity.getComponent(GraphicComponent.class).get().getWidth() / 100 * this.dpi;
        height = entity.getComponent(GraphicComponent.class).get().getHeight() / 100 * this.dpi;
        graphicContext.setFill(Color.WHITE);
        graphicContext.fillOval(entityX - width / 2, entityY - height / 2, width, height);
    }
}
