package it.unibo.t2sgame.input.impl;

import java.util.Random;

import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.input.api.AbstractChasingAIInputController;
import it.unibo.t2sgame.input.api.Directions;

/**
 * AI that randomly change direction every TIME_TO_NEXT_DECISION ms 
 */
public class MosquitoAIInputController extends AbstractChasingAIInputController {
    private final Random random = new Random();
    private static final long TIME_TO_NEXT_DECISION = 500;
    private long lastChoiceTime = 0;

    @Override
    protected void computeNextCommand() {
        this.commandsQueue.add(entity -> {
            if (isTimeToChoose()) {
                entity.notifyComponent(PhysicsComponent.class,
                    () -> Directions.values()[random.nextInt(Directions.values().length)]);
                this.lastChoiceTime = System.currentTimeMillis();
            }
        });
    }

    private boolean isTimeToChoose(){
		return System.currentTimeMillis() - lastChoiceTime > TIME_TO_NEXT_DECISION;
	}

}
