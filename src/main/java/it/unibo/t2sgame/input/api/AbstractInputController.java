package it.unibo.t2sgame.input.api;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This abstract class represent an InutController with a default implementation of
 * the method getCommandsQueue
 */
public abstract class AbstractInputController implements InputController {
    protected final Queue<Command> commandsQueue = new ConcurrentLinkedQueue<>();

    /**
     * Return a defensive copy of the commands queue
     */
    @Override
    public Queue<Command> getCommandsQueue() {
        var defensiveQueue = new ConcurrentLinkedQueue<>(this.commandsQueue);
        this.commandsQueue.clear();
        return defensiveQueue;
    }
}
