package it.unibo.t2sgame.game.ecs.api;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.game.ecs.impl.GraphicComponent;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;

/**
 * This interface models a factory for Components.
 * A Component abstract the concept of "Component" of Entity-Component-System
 * architecture.
 */
public interface ComponentFactory {
    /**
     * @param inputController the InputController linked to the component
     * @return an Input component based on {@link inputController}
     */
    Component createInputComponentFrom(InputController inputController);

    /**
     * @param speed the speed of the entity
     * @return a physics component based on {@link speed}.
     */
    Component createPhysicsComponentFrom(double speed);

    /**
     * @param speed      the speed of the entity
     * @param directions the initial direction of the entity
     * @return a physics component based on {@link speed} and {@link direction}
     */
    Component createPhysicsComponentFrom(double speed, Directions directions);

    /**
     * @param shape   the shape of the bounding box
     * @param isRigid a boolean representing the fact the entity is an solid form,
     *                so the entities
     *                which collide with it can't go through its body
     * @param types   the types of entity the entity collides with
     * @return a collision component based on {@link shape} and {@link isRigid}
     */
    Component createCollisionComponentFrom(Shape shape, boolean isRigid, List<Type> types);

    /**
     * @param shape   the shape of the bounding box
     * @param isRigid a boolean representing the fact the entity is an solid form,
     *                so the entities
     *                which collide with it can't go through its body
     * @param types   the types of entity the entity collides with
     * @return a collision component based on {@link shape} and {@link isRigid} wich
     *         destroys the entity on collision
     */
    Component createProjectileCollisionComponentFrom(Shape shape, boolean isRigid, List<Type> types);

    /**
     * @param health an integer represents the heal-points of the player
     * @return an health component based on {@link health} heal-points.
     */
    Component createHealthComponentFrom(int health);

    /**
     * @param fireRateSeconds  a double represents the shots rate of fire in seconds
     * @param projectileSpeed  a double represents the shots speed
     * @param projectileDamage a int represents the shots damage
     * @param projectileSize   a double represents the shots size
     * @return an ShootComponent based on a {@link fireRate}.
     */
    Component createShootComponentFrom(double fireRateSeconds, double projectileSpeed, int projectileDamage,
            double projectileSize);

    /**
     * @param damage   the damage of the entity which collide with another one.
     * @param cooldown a cooldown which represents the seconds where the damage
     *                 component can't hurt other entity.
     * @return a damage component based on {@link damage} and {@link cooldown}
     */
    Component createDamageComponentFrom(int damage, double cooldown);

    /**
     * @param spriteName name of the sprite cached in the Graphics used
     * @param width      the width of the GraphicComponent
     * @param height     the height of the GraphicComponent
     * @return a GraphicComponent based on {@link spriteName}, {@link width} and
     *         {@link height}
     */
    GraphicComponent createGraphicComponentWithSprite(String spriteName, double width, double height);

    /**
     * @param width  the width of the GraphicComponent
     * @param height the height of the GraphicComponent
     * @return a GraphicComponent having Circular shape and based on {@link width}
     *         and {@link height}
     */
    GraphicComponent createCircleGraphicComponent(double width, double height);

    /**
     * @param width  the width of the GraphicComponent
     * @param height the height of the GraphicComponent
     * @return a GraphicComponent having Rectangular shape and based on
     *         {@link width} and {@link height}
     */
    GraphicComponent createRectangleGraphicComponent(double width, double height);
}
