package it.unibo.t2sgame.core.components.api;

import java.util.List;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.impl.GraphicComponent;
import it.unibo.t2sgame.game.components.TypeComponent.Type;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;

/**
 * This interface models a factory for Components.
 * A Component abstract the concept of "Component" of Entity-Component-System architecture.
 */
public interface ComponentFactory {
    /**
     * @param ic the InputController linked to the component
     * @return an Input component based on {@link ic} InputController
     */
    Component createInputComponentFrom(InputController inputController);
    /**
     * @return a physics component based on {@link speed}.
     */
    Component createPhysicsComponentFrom(double speed);
    /**
     * @param speed the speed of the entity
     * @param directions the initial direction of the entity
     * @return a physics component based on {@link speed} and {@link direction}
     */
    Component createPhysicsComponentFrom(double speed, Directions directions);
    /**
     * @param shape the shape of the bounding box
     * @param isRigid a boolean representing the fact the entity is an solid form, so the entities 
     *                which collide with it can't go through its body
     * @return a collision component based on {@link shape} and {@link isRigid}
     */
    Component createCollisionComponentFrom(Shape shape, boolean isRigid, List<Type> types);
    /**
     * @param shape the shape of the bounding box
     * @param isRigid a boolean representing the fact the entity is an solid form, so the entities 
     *                which collide with it can't go through its body
     * @return a collision component based on {@link shape} and {@link isRigid} wich destroys the entity on collision
     */
    Component createProjectileCollisionComponentFrom(Shape shape, boolean isRigid, List<Type> types);
    /**
     * @param health an integer represents the heal-points of the player
     * @return an health component based on {@link health} heal-points.
     */
    Component createHealthComponentFrom(int health);
    /**
     * @param fireRate a double represents the shoot's fireRate
     * @return an ShootComponent based on a {@link fireRate}.
     */
    Component createShootComponentFrom(double fireRate);
    /**
     * @param damage the damage of the entity which collide with another one.
     * @param cooldown a cooldown which represents the seconds where the damage component can't hurt other entity.
     * @return a damage component based on {@link damage} and {@link cooldown}
     */
    Component createDamageComponentFrom(int damage , double cooldown);

    GraphicComponent createGraphicComponentWithSprite(String spriteName, double width, double height);

    GraphicComponent createCircleGraphicComponent(double width, double height);
    
    GraphicComponent createRectangleGraphicComponent(double width, double height);
    
}
