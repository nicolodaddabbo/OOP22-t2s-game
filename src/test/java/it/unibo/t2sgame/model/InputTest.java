package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.input.api.InputComponentFactory;
import it.unibo.t2sgame.input.impl.InputComponentFactoryImpl;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.model.impl.EntityImpl;

public class InputTest {
    private final InputComponentFactory factory;
    
    public InputTest() {
        this.factory = new InputComponentFactoryImpl();
    }

    @Test
    void keyboardInputTest() {
        var keyboardInputComponent = this.factory.createKeyboardInputComponent();
        var entity = new EntityImpl(new Vector2D(0, 0));

        // add the input controller to the input component
        entity.addComponent(keyboardInputComponent);

        KeyboardInputController inputController = (KeyboardInputController) keyboardInputComponent.getInputController();
        
        // with no key pressed the command should be empty
        assertTrue(inputController.getCommand().isEmpty());

        // 'W' key is pressed 
        inputController.notifyKeyPressed(87);
        keyboardInputComponent.update(entity);

        // command should be Move in UP direction
        assertTrue(inputController.getCommand().isPresent());
    }

}
