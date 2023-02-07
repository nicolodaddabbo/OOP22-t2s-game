package it.unibo.t2sgame.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.logics.api.State;
import it.unibo.t2sgame.game.model.api.World;

class GameTest {
    /**
     * A factory which creates Game's objects
     */
    private GameFactory factory = new GameFactoryImpl();

    void testBasics(final Game g) {
        var w = g.getWorld();
        var s = g.getState();
        var players = w.getPlayers();
        // Let update the game status
        g.update();
        // 1 Round
        testRound(w, s, 1);
        g.update();
        // 2 Round
        testRound(w, s, 2);
        g.update();
        // 3 Round
        testRound(w, s, 3);
        g.update();
        // Players has lost
        w.removeEntities(players);
        assertTrue(g.isOver());
    }

    void testRound(final World world, final State state, int round) {
        assertEquals(round, state.getRound());
        world.getCurrentWave().ifPresent(wave -> {
            world.removeEntities(wave.getEnemies());
            wave.getEnemies().clear();
        });
        assertTrue(state.isWaveOver(world.getCurrentWave()));
    }

    @Test
    void testSinglePlayer() {
        // Creating a single player game
        var game = this.factory.createSinglePlayerGame();
        // Check if the game contains only one player
        assertEquals(1, game.getWorld().getPlayers().size());
        testBasics(game);
    }

    @Test
    void testMultiPlayerWithCompanion() {
        var game = this.factory.createMultiPlayerGame();
        // Check if companion is present
        var companion = game.getWorld().getEntities().stream()
                .filter(e -> e.getType() == Type.COMPANION)
                .findFirst();
        assertTrue(companion.isPresent());
        // Test basics
        testBasics(game);
    }

}
