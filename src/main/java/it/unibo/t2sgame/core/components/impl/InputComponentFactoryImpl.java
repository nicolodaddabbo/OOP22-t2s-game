package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.AbstractComponent;
import it.unibo.t2sgame.core.components.api.InputComponent;
import it.unibo.t2sgame.core.components.api.InputComponentFactory;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.input.api.InputController;
import it.unibo.t2sgame.input.impl.BasicEnemyAIInputController;
import it.unibo.t2sgame.input.impl.KeyboardInputController;

public class InputComponentFactoryImpl implements InputComponentFactory {

    private class InputComponentImpl extends AbstractComponent implements InputComponent {
        private final InputController inputController;

        public InputComponentImpl(final InputController inputController) {
            this.inputController = inputController;
        }

        @Override
        public <T> void receive(final Message<T> message) {
            // an input component doesn't need to receive messages
        }

        @Override
        public InputController getInputController() {
            return this.inputController;
        }

        @Override
        public void update() {
            this.inputController.getCommand().ifPresent(c -> c.execute(this.entity));
        }

    }

    @Override
    public InputComponent createBasicEnemyAIInputComponent() {
        return new InputComponentImpl(new BasicEnemyAIInputController());
    }

    @Override
    public InputComponent createKeyboardInputComponent() {
        return new InputComponentImpl(new KeyboardInputController());
    }

}
