package it.unibo.t2sgame.input.api;

import java.util.Optional;

/**
 * This interface represent an InutController that gets notified when an input occours
 * and asociate a Command to that given input
 */
public interface InputController {
    /**
     * Convert an input to the associated Command
     * @return an Optional of the Command to execute, an empty Optional if no Command is associated 
     * to the given input
     */
    Optional<Command> getCommand();
}
