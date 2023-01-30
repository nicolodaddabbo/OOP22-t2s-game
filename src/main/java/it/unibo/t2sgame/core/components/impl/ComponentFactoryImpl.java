package it.unibo.t2sgame.core.components.impl;

import java.util.function.BiConsumer;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
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
    public Component createCollisionComponentFrom(Shape shape, boolean isRigid) {
        return new BaseCollisionComponent(shape, isRigid);
    }

    @Override
    public Component createProjectileCollisionComponentFrom(Shape shape, boolean isRigid) {
        return new ProjectileCollisionComponent(shape, isRigid);
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
    
    private GraphicComponent fromGraphicFunction(BiConsumer<Graphic, Entity> graphConsumer){
        return new GraphicComponent() {
            @Override
            public void update() {
                graphConsumer.accept(this.graphic, this.entity);
            }
        };
    }

    @Override
    public GraphicComponent createPlayerGraphicComponent() {    
        return fromGraphicFunction((graphic, entity) -> graphic.drawPlayer(entity));
    }

    @Override
    public GraphicComponent createProjectileGraphicComponent() {
        return fromGraphicFunction((graphic, entity) -> graphic.drawProjectile(entity));
    }

    @Override
    public GraphicComponent createBaseEnemyGraphicComponent() {
        return fromGraphicFunction((graphic, entity) -> graphic.drawBaseEnemy(entity));
    }
    
}
