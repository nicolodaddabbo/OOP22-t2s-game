package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.view.api.Graphic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphicJavaFXImpl implements Graphic{

    private final GraphicsContext gc;

    GraphicJavaFXImpl(GraphicsContext gc){
        this.gc = gc;
    }
    
    @Override
    public void drawPlayer(Entity player) {
        var player_x = player.getPosition().getX();
        var player_y = player.getPosition().getY();
        
        gc.setFill(Color.GREEN);
        gc.fillRect(player_x, player_y, 60, 80);
    }

    @Override
    public void drawProjectile(Entity projectile) {
        
    }

    @Override
    public void drawBaseEnemy(Entity enemy) {
        var enemy_x = enemy.getPosition().getX();
        var enemy_y = enemy.getPosition().getY();

        gc.setFill(Color.RED);
        gc.fillRect(enemy_x, enemy_y, 60, 80);
    }
    
}
