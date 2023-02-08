package it.unibo.t2sgame.core.components.impl;

import java.util.List;
import java.util.function.BiConsumer;

import it.unibo.t2sgame.common.Shape;
import it.unibo.t2sgame.core.components.api.Component;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.components.BaseCollisionComponent;
import it.unibo.t2sgame.game.components.DamageComponent;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ProjectileCollisionComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;
import it.unibo.t2sgame.view.api.Graphic;

public class ComponentFactoryImpl implements ComponentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createInputComponentFrom(final InputController inputController) {
        return new InputComponent(inputController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createPhysicsComponentFrom(final double speed) {
        return new PhysicsComponent(speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createPhysicsComponentFrom(final double speed, final Directions directions) {
        return new PhysicsComponent(speed, directions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createCollisionComponentFrom(final Shape shape, final boolean isRigid, final List<Type> types) {
        return new BaseCollisionComponent(shape, isRigid, types);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createProjectileCollisionComponentFrom(final Shape shape, final boolean isRigid,
            final List<Type> types) {
        return new ProjectileCollisionComponent(shape, isRigid, types);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createHealthComponentFrom(final int health) {
        return new HealthComponent(health);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createShootComponentFrom(final double fireRateSeconds, final double projectileSpeed,
            final int projectileDamage, final double projectileSize) {
        return new ShootComponent(fireRateSeconds, projectileSpeed, projectileDamage, projectileSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component createDamageComponentFrom(final int damage, final double cooldown) {
        return new DamageComponent(damage, cooldown);
    }

    private GraphicComponent fromGraphicFunction(final BiConsumer<Graphic, Entity> graphConsumer,
            final double width, final double height) {
        return new GraphicComponent(width, height) {
            @Override
            public void update() {
                graphConsumer.accept(this.getGraphic(), this.entity);
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicComponent createGraphicComponentWithSprite(final String spriteName, final double width,
            final double height) {
        return fromGraphicFunction((graphic, entity) -> graphic.drawFromSprite(entity, spriteName), width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicComponent createCircleGraphicComponent(final double width, final double height) {
        return fromGraphicFunction(Graphic::drawCircle, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicComponent createRectangleGraphicComponent(final double width, final double height) {
        return fromGraphicFunction(Graphic::drawRectangle, width, height);
    }
}