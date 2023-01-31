package it.unibo.t2sgame.input.api;

import java.util.Queue;

/**
 * This interface represent an InutController that gets notified when an input occours
 * and asociate a Command to that given input
 */
public interface InputController {
    /**
     * Return the queue of commands to be executed
     * @return the commands queue
     */
    Queue<Command> getCommandsQueue();
}
