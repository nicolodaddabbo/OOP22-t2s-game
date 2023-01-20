package it.unibo.t2sgame.input.api;

import it.unibo.t2sgame.model.api.Component;

/**
 * This interface represent the InputComponent of the entities
 */
public interface InputComponent extends Component {
    InputController getInputController();
}
