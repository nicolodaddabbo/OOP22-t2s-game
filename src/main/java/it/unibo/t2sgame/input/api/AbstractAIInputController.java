package it.unibo.t2sgame.input.api;

import java.util.Queue;

/**
 * This abstract class represent an InutController for an AI with a default implementation of
 * the method getCommandsQueue
 */
public abstract class AbstractAIInputController extends AbstractInputController {

    /**
     * This method adds the next command to be executed by the AI controlled entity
     */
    protected abstract void computeNextCommand();

    /**
     * This template method return the commands queue (in the way that is implemented 
     * in the parent class) but first it execute the computeNextCommand method
     */
    @Override
    public Queue<Command> getCommandsQueue() {
        computeNextCommand();
        return super.getCommandsQueue();
    }

}
