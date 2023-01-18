package it.unibo.t2sgame.input.impl;

import it.unibo.t2sgame.input.api.InputComponent;
import it.unibo.t2sgame.input.api.InputComponentFactory;
import it.unibo.t2sgame.input.api.InputController;
import it.unibo.t2sgame.model.api.Entity;

public class InputComponentFactoryImpl implements InputComponentFactory {

    private class InputComponentImpl implements InputComponent {
        private final InputController inputController;

        public InputComponentImpl(final InputController inputController) {
            this.inputController = inputController;
        }

        @Override
        public void receive() {
            // TODO Auto-generated method stub
        }

        @Override
        public void update(final Entity entity) {
            this.inputController.getCommand().ifPresent(c -> c.execute(entity));
        }

    }

    @Override
    public InputComponent createAIInputComponent() {
        return null;
    }

    @Override
    public InputComponent createKeyboardInputComponent() {
        return new InputComponentImpl(new KeyboardInputController());
    }

}
