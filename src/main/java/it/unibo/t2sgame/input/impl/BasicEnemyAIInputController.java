package it.unibo.t2sgame.input.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import it.unibo.t2sgame.input.api.Command;
import it.unibo.t2sgame.input.api.InputController;

public class BasicEnemyAIInputController implements InputController {
    final List<Command> availableCommands = List.of(new MoveUp(), new MoveDown(), new MoveRight(), new MoveLeft());

    @Override
    public Optional<Command> getCommand() {
        final Random random = new Random();
        return Optional.of(availableCommands.get(random.nextInt(availableCommands.size())));
    }
    
}
