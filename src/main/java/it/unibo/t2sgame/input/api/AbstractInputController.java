package it.unibo.t2sgame.input.api;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractInputController implements InputController {
    protected final Queue<Command> commandsQueue = new ConcurrentLinkedQueue<>();

    @Override
    public Queue<Command> getCommandsQueue() {
        var defensiveQueue = new ConcurrentLinkedQueue<>(this.commandsQueue);
        this.commandsQueue.clear();
        return defensiveQueue;
    }
}
