```mermaid
classDiagram

InputController --* InputComponent
AbstractInputController --|> InputController
KeyboardInputController --|> AbstractInputController

%%
class InputComponent {
    +update(): void
}
%%
class InputController {
    <<interface>>
    +getCommandsQueue(): Queue~Command~
}
%%
class AbstractInputController {
    <<abstract>>
    #addToCommandsQueue(Command): void
    +getCommandsQueue(): Queue~Command~
}
%%
class KeyboardInputController {
    +notifyKeyPressed(int): void
    +notifyKeyReleased(int): void
}
