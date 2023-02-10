package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.components.HealthComponent;

class HealthComponentTest {
    private static final int STARTING_HEALTH = 3;
    private static final int DAMAGE = 1;

    private Entity createBaseEntity() {
        return new EntityImpl(new Vector2D(0, 0), Type.PLAYER)
            .addComponent(new HealthComponent(STARTING_HEALTH));
    }

    @Test
    void testDamageReceived() {
        final var entity = createBaseEntity();
        entity.notifyComponent(HealthComponent.class, () -> DAMAGE);
        assertEquals(STARTING_HEALTH - DAMAGE, entity.getComponent(HealthComponent.class).get().getHealth());
    }

    @Test
    void testBehaviourWhenDamageIsBiggerThanCurrentHealth() {
        final var entity = createBaseEntity();
        entity.notifyComponent(HealthComponent.class, () -> STARTING_HEALTH * 2);
        assertEquals(0, entity.getComponent(HealthComponent.class).get().getHealth());
    }
}
