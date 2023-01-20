package it.unibo.t2sgame.input.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.Directions;
import it.unibo.t2sgame.input.api.InputController;

public class BasicEnemyAIInputController implements InputController {
    private final List<Command> availableCommands;
    private final Random random = new Random();

    public BasicEnemyAIInputController() {
        this.availableCommands = new LinkedList<>();
        for (var direction : Directions.values()) {
            this.availableCommands.add(new Move(direction));
        }
    }

    @Override
    public Optional<Command> getCommand() {
        return Optional.of(availableCommands.get(random.nextInt(availableCommands.size())));
    }
    
}
