package it.unibo.t2sgame.components.api;

public interface GraphicComponentFactory {
    /**
     * 
     * @return the Graphic component of a player Entity
     */
    GraphicComponent getPlayerGraphicComponent();
    /**
     * 
     * @return the Graphic component of a projectile Entity
     */
    GraphicComponent getProjectileGraphicComponent();
    /**
     * 
     * @return the Graphic component of a basic enemy Entity
     */
    GraphicComponent getBaseEnemyGraphicComponent();
}
