package it.unibo.t2sgame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.t2sgame.common.Vector2D;
import it.unibo.t2sgame.core.components.api.ComponentFactory;
import it.unibo.t2sgame.core.components.impl.ComponentFactoryImpl;
import it.unibo.t2sgame.core.components.impl.InputComponent;
import it.unibo.t2sgame.core.components.impl.PhysicsComponent;
import it.unibo.t2sgame.core.entity.api.Entity;
import it.unibo.t2sgame.core.entity.api.Type;
import it.unibo.t2sgame.game.model.api.EntityFactory;
import it.unibo.t2sgame.game.model.api.World;
import it.unibo.t2sgame.game.model.api.WorldFactory;
import it.unibo.t2sgame.game.model.impl.EntityFactoryImpl;
import it.unibo.t2sgame.game.model.impl.WorldFactoryImpl;
import it.unibo.t2sgame.input.impl.ChasingAIInputController;
import it.unibo.t2sgame.input.impl.KeyboardInputController;
import it.unibo.t2sgame.input.impl.Move;
import it.unibo.t2sgame.input.impl.Shoot;

/**
 * Class that contains input related tests.
 */
class InputTest {
    private static final int MOVE_UP_CODE = 38;
    private static final int SHOOT_UP_CODE = 87;
    private final ComponentFactory componentFactory = new ComponentFactoryImpl();
    private final EntityFactory entityFactory = new EntityFactoryImpl();
    private final WorldFactory worldFactory = new WorldFactoryImpl();
    private final Entity player;
    private final Entity enemy;
    
    public InputTest() {
        final World world = this.worldFactory.createWorldWithOnePlayer();
        this.player = world.getPlayers().get(0);
        this.enemy = this.entityFactory.createBaseEnemy(new Vector2D(0, 0));
        world.addEntity(this.enemy);
        world.addEntity(this.player);
    }

    @Test
    void keyboardInputTest() {
        final InputComponent keyboardInputComponent = (InputComponent) this.componentFactory
                .createInputComponentFrom(new KeyboardInputController());
        // add the input component to the player
        this.player.addComponent(keyboardInputComponent);
        final KeyboardInputController inputController = (KeyboardInputController) keyboardInputComponent
                .getInputController();
        // with no key pressed the commands queue should be empty
        assertTrue(inputController.getCommandsQueue().isEmpty());
        // 'Arrow UP' key is pressed
        inputController.notifyKeyPressed(MOVE_UP_CODE);
        // command should be a Shoot command
        assertEquals(Shoot.class, inputController.getCommandsQueue().poll().getClass());
        // 'W' key is pressed
        inputController.notifyKeyPressed(SHOOT_UP_CODE);
        // command should be a Move command, and the previous Shoot command shouldn't
        // exists
        assertEquals(Move.class, inputController.getCommandsQueue().poll().getClass());
    }

    @Test
    void chasingAIInputTest() {
        final InputComponent aiInputComponent = (InputComponent) this.componentFactory
                .createInputComponentFrom(new ChasingAIInputController(Type.PLAYER));
        this.enemy.addComponent(aiInputComponent);
        final ChasingAIInputController inputController = (ChasingAIInputController) aiInputComponent
                .getInputController();
        // every time the getCommandsQueue method gets called the AI generate a command,
        // so the
        // commands queue should never be empty
        assertFalse(inputController.getCommandsQueue().isEmpty());
        aiInputComponent.update();
        this.enemy.getComponent(PhysicsComponent.class).get().update();
        // a ChasingAIInputController generates only Move commands, so after an update
        // the position
        // of the player should be different from the initial one
        assertNotEquals(this.enemy.getPosition(), new Vector2D(0, 0));
    }

}
