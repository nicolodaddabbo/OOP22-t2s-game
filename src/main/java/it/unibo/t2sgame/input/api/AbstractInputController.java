package it.unibo.t2sgame.input.api;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This abstract class represent an InutController with a default implementation of
 * the method getCommandsQueue.
 */
public abstract class AbstractInputController implements InputController {
    private final Queue<Command> commandsQueue = new ConcurrentLinkedQueue<>();

    /**
     * Add command to the commands queue.
     * 
     * @param command to add
     */
    protected void addToCommandsQueue(final Command command) {
        this.commandsQueue.add(command);
    }

    /**
     * Return a defensive copy of the commands queue and clears it.
     */
    @Override
    public Queue<Command> getCommandsQueue() {
        var defensiveQueue = new ConcurrentLinkedQueue<>(this.commandsQueue);
        this.commandsQueue.clear();
        return defensiveQueue;
    }
}
