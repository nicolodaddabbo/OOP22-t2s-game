package it.unibo.t2sgame.components.api;

import it.unibo.t2sgame.input.api.InputController;

/**
 * This interface represent the InputComponent of the entities
 */
public interface InputComponent extends Component {
    /**
     * @return the InputComponent's InputController
     */
    InputController getInputController();
}
