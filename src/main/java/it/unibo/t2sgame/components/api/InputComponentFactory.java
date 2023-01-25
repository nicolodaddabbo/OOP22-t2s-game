package it.unibo.t2sgame.components.api;

/**
 * An InputComponent Factory to produce InputComponent instance.
 */
public interface InputComponentFactory {
    /**
     * @return an InputComponent that gets input from a Keyboard
     */
    InputComponent createKeyboardInputComponent();

    /**
     * @return an InputComponent that gets input from an AI
     */
    InputComponent createBasicEnemyAIInputComponent();
}
