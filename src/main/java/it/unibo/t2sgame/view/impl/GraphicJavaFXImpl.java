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
/**
 * class that represents the Graphics using JavaFX.
 */
public class GraphicJavaFXImpl implements Graphic {
    private final GraphicsContext graphicContext;
    private Map<String, Image> cachedSprites;
    private double entityX;
    private double entityY;
    private double dpiW;
    private double dpiH;
    private double width;
    private double height;

    GraphicJavaFXImpl(final GraphicsContext gc, final double dpiW, final double dpiH) {
        this.graphicContext = gc;
        this.dpiW = dpiW;
        this.dpiH = dpiH;
        storeSprites();
    }

    private void storeSprites() {
        cachedSprites = new HashMap<>();
        try {
            this.cachedSprites.put("player", new Image(new FileInputStream("src/main/resources/sprites/ghost.gif")));
            this.cachedSprites.put("companion", new Image(new FileInputStream("src/main/resources/sprites/companion.gif")));
            this.cachedSprites.put("fire_enemy", new Image(new FileInputStream("src/main/resources/sprites/fire_enemy.gif")));
            this.cachedSprites.put("ice_enemy", new Image(new FileInputStream("src/main/resources/sprites/ice_enemy.gif")));
            this.cachedSprites.put("rainbow_enemy", 
                new Image(new FileInputStream("src/main/resources/sprites/rainbow_enemy.gif")));
            this.cachedSprites.put("crown_enemy", new Image(new FileInputStream("src/main/resources/sprites/crown_enemy.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFromSprite(final Entity entity, final String spirteName) {
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
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final Entity entity) {
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
    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCircle(final Entity entity) {
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
