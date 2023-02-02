package it.unibo.t2sgame.input.api;

import java.util.Queue;

public abstract class AbstractAIInputController extends AbstractInputController {

    protected abstract void computeNextCommand();

    @Override
    public Queue<Command> getCommandsQueue() {
        computeNextCommand();
        return super.getCommandsQueue();
    }
    
}
