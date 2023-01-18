package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.input.api.InputComponentFactory;
import it.unibo.t2sgame.input.impl.InputComponentFactoryImpl;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.input.impl.MoveUp;
import it.unibo.t2sgame.model.impl.EntityImpl;

public class InputTest {
    private final InputComponentFactory factory;
    
    public InputTest() {
        this.factory = new InputComponentFactoryImpl();
    }

    @Test
    void keyboardInputTest() {
        var keyboardInputComponent = this.factory.createKeyboardInputComponent();
        var entity = new EntityImpl();
        keyboardInputComponent.update(entity);
    }

    @Test
    void keyboardCommandTest() {
        var keyboardInputController = new KeyboardInputController();
        assertEquals(Optional.empty(), keyboardInputController.getCommand());
        keyboardInputController.notifyKeyPressed(38);
        assertTrue(keyboardInputController.getCommand().get() instanceof MoveUp);
    }

}
