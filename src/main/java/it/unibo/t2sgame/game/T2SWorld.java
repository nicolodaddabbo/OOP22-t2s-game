package it.unibo.t2sgame.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.t2sgame.core.AbstractEnviroment;
import it.unibo.t2sgame.core.components.impl.CollisionComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.game.model.api.Wave;

public class T2SWorld extends AbstractEnviroment {

    private final List<Entity> players = new ArrayList<>();
    private Optional<Wave> currentWave = Optional.empty();

    public Optional<Wave> getCurrentWave() {
        return this.currentWave;
    }

    public List<Entity> getPlayers() {
        return new ArrayList<>(this.players);
    }
    
    public void setWave(final Wave next) {
        this.currentWave = Optional.of(next);
        next.getEnemies()
            .forEach(e -> e.getComponent(CollisionComponent.class)
                .get()
                .setCollisions(this.getPlayers().stream()
                    .map(p -> p.getComponent(CollisionComponent.class).get())
                    .collect(Collectors.toSet())));
        /* Adding all enemies to the entities */
        next.getEnemies().forEach(this::addEntity);
    }
}
