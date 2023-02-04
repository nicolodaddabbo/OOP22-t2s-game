package it.unibo.t2sgame.game.model.impl;

import java.util.function.Consumer;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.components.DamageComponent;
import it.unibo.t2sgame.game.components.HealthComponent;
import it.unibo.t2sgame.game.components.ShootComponent;
import it.unibo.t2sgame.game.model.api.PowerUp;
import it.unibo.t2sgame.game.model.api.PowerUpFactory;

public class PowerUpFactoryImpl implements PowerUpFactory{

    public PowerUp fromFunction(Consumer<Entity> function){
        return function::accept;
    }

    @Override
    public PowerUp generateDamageBoostPowerUp(Entity entity) {
        return fromFunction(en -> entity.getComponent(DamageComponent.class).ifPresent(c -> c.setDamage(c.getDamage() + 1)));
    }

    @Override
    public PowerUp generateFireRatioPowerUp(Entity entity) {
        return fromFunction(en -> entity.getComponent(ShootComponent.class).ifPresent(c -> c.setfireRateSeconds(c.getfireRateSeconds() / 2)));
    }

    @Override
    public PowerUp generateHealthUpPowerUp(Entity entity) {
        return fromFunction(en -> entity.getComponent(HealthComponent.class).ifPresent(c -> c.setHealth(c.getHealth() + 1)));
    }
}
