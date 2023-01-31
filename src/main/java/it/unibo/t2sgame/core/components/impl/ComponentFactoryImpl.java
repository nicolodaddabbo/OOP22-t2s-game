package it.unibo.t2sgame.core.components.impl;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.BaseCollisionComponent;
import it.unibo.t2sgame.game.components.DamageComponent;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ProjectileCollisionComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.components.TypeComponent.Type;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;
import it.unibo.t2sgame.view.api.Graphic;

public class ComponentFactoryImpl implements ComponentFactory {

    @Override
    public Component createInputComponentFrom(final InputController inputController) {
        return new InputComponent(inputController);
    }

    @Override
    public Component createPhysicsComponentFrom(double speed) {
        return new PhysicsComponent(speed);
    }

    @Override
    public Component createPhysicsComponentFrom(double speed, Directions directions) {
        return new PhysicsComponent(speed, directions);
    }

    @Override
    public Component createCollisionComponentFrom(Shape shape, boolean isRigid, List<Type> types) {
        return new BaseCollisionComponent(shape, isRigid, types);
    }

    @Override
    public Component createProjectileCollisionComponentFrom(Shape shape, boolean isRigid, List<Type> types) {
        return new ProjectileCollisionComponent(shape, isRigid, types);
    }

    @Override
    public Component createHealthComponentFrom(int health) {
        return new HealthComponent(health);
    }

    @Override
    public Component createShootComponentFrom(double fireRate) {
        return new ShootComponent(fireRate);
    }

    @Override
    public Component createDamageComponentFrom(int damage, double cooldown) {
        return new DamageComponent(damage, cooldown);
    }
    
    private GraphicComponent fromGraphicFunction(BiConsumer<Graphic, Entity> graphConsumer, double width, double height){
        return new GraphicComponent(width, height) {
            @Override
            public void update() {
                graphConsumer.accept(this.graphic, this.entity);
            }
        };
    }

    @Override
    public GraphicComponent createGraphicComponentWithSprite(String spriteName, double width, double height) {    
        return fromGraphicFunction((graphic, entity) -> graphic.drawFromSprite(entity, spriteName), width, height);
    }

    @Override
    public GraphicComponent createCircleGraphicComponent(double width, double height) {
        return fromGraphicFunction(Graphic::drawCircle, width, height);
    }

    @Override
    public GraphicComponent createRectangleGraphicComponent(double width, double height) {
        return fromGraphicFunction(Graphic::drawRectangle, width, height);
    }
    
}
