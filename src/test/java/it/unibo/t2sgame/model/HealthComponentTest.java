package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.core.entity.impl.EntityImpl;
import it.unibo.t2sgame.game.components.HealthComponent;

public class HealthComponentTest {
    private int startingHealth = 3;
    private int damage = 1;
    private Entity createBaseEntity() {
        return new EntityImpl(new Vector2D(0, 0), Type.PLAYER)
            .addComponent(new HealthComponent(startingHealth));
    }

    @Test
    public void testStartHealth() {
        var entity = createBaseEntity();
        assertTrue(entity.getComponent(HealthComponent.class).isPresent());
        assertEquals(this.startingHealth, entity.getComponent(HealthComponent.class).get().getHealth());
    }

    @Test
    public void testDamageReceived() {
        var entity = createBaseEntity();
        assertTrue(entity.getComponent(HealthComponent.class).isPresent());
        assertEquals(this.startingHealth, entity.getComponent(HealthComponent.class).get().getHealth());
        entity.notifyComponent(HealthComponent.class, () -> this.damage);
        assertEquals(this.startingHealth - this.damage, entity.getComponent(HealthComponent.class).get().getHealth());
    }

    @Test
    public void testBehaviourWhenDamageIsBiggerThanCurrentHealth(){
        var entity = createBaseEntity();
        assertTrue(entity.getComponent(HealthComponent.class).isPresent());
        assertEquals(this.startingHealth, entity.getComponent(HealthComponent.class).get().getHealth());
        entity.notifyComponent(HealthComponent.class, () -> this.startingHealth*2);
        assertEquals(0, entity.getComponent(HealthComponent.class).get().getHealth());
    }
}
