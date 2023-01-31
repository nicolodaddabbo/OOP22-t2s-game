package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.input.api.InputController;

public class InputComponent extends AbstractComponent {
    private final InputController inputController;

    public InputComponent(final InputController inputController) {
        this.inputController = inputController;
    }

    @Override
    public <T> void receive(final Message<T> message) {
        // an input component doesn't need to receive messages
    }

    public InputController getInputController() {
        return this.inputController;
    }

    @Override
    public void update() {
        var commandsQueue = this.inputController.getCommandsQueue();
        commandsQueue.forEach(c -> c.execute(this.entity));
        commandsQueue.clear();
    }
}
