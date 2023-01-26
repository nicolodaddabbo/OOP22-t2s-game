package it.unibo.t2sgame.core.components.impl;

import it.unibo.t2sgame.core.components.api.InputComponent;
import it.unibo.t2sgame.core.components.api.InputComponentFactory;
import it.unibo.t2sgame.core.components.api.Message;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.input.api.InputController;
import it.unibo.t2sgame.input.impl.BasicEnemyAIInputController;
import it.unibo.t2sgame.input.impl.KeyboardInputController;

public class InputComponentFactoryImpl implements InputComponentFactory {

    private class InputComponentImpl implements InputComponent {
        private final InputController inputController;

        public InputComponentImpl(final InputController inputController) {
            this.inputController = inputController;
        }

        @Override
        public <T> void receive(Message<T> message) {
            // TODO Auto-generated method stub
        }

        @Override
        public void update(final Entity entity) {
            this.inputController.getCommand().ifPresent(c -> c.execute(entity));
        }

        @Override
        public InputController getInputController() {
            return this.inputController;
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
