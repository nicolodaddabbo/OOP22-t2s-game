```mermaid
classDiagram

InputComponent <|-- AbstractInputComponent
AbstractInputComponent <|-- KeyboardInputComponent
AbstractInputComponent <|-- AIInputComponent
InputController --* AbstractInputComponent
InputController <|-- KeyboardInputController
InputController <|-- AIInputController
Command --* InputController

%%
class InputComponent {
    <<interface>>
    +update() void
}
%%
class AbstractInputComponent {
    <<abstract class>>
    #setInputController()
    +update() void
}
%%
class KeyboardInputComponent {
    #setInputController()
}
%%
class AIInputComponent {
    #setInputController()
}
%%
class InputController {
    <<interface>>
    +handleInput(): Optional~Command~
}
%%
class KeyboardInputController {
    +handleInput(): Optional~Command~
    +notifyKeyboardPressed(int keyCode): void
}
%%
class AIInputController {
    +handleInput(): Optional~Command~
    +generateNextMove(): void
}
%%
class Command {
    <<interface>>
    +execute(Entity entity): void
}