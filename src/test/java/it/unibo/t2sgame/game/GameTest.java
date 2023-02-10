package it.unibo.t2sgame.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.game.ecs.api.Type;
import it.unibo.t2sgame.game.ecs.impl.HealthComponent;

class GameTest {

    private final GameFactory factory = new GameFactoryImpl();

    void testBasics(final Game g) {
        final var w = g.getWorld();
        assertNotNull(g.getState());
        final var s = g.getState();
        // Check that the the world getter from the world is a defency copy
        assertNotEquals(w, g.getWorld());
        final var players = w.getPlayers();
        // Let update the game status
        g.update();
        // Check if game has started
        assertEquals(1, s.getRound());
        assertFalse(g.isOver());
        players.forEach(p -> {
            p.notifyComponent(HealthComponent.class, () -> 100);
        });
        g.update();
        assertTrue(g.isOver());
    }

    @Test
    void testSinglePlayer() {
        // Creating a single player game
        final var game = this.factory.createSinglePlayerGame();
        // Check if the game contains only one player
        assertEquals(1, game.getWorld().getPlayers().size());
        testBasics(game);
    }

    @Test
    void testMultiPlayerWithCompanion() {
        final var game = this.factory.createWithCompanion();
        // Check if companion is present
        final var companion = game.getWorld().getEntities().stream()
                .filter(e -> e.getType() == Type.COMPANION)
                .findFirst();
        assertTrue(companion.isPresent());
        // Test basics
        testBasics(game);
    }

}
