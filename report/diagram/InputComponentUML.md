```mermaid
classDiagram

InputController --* InputComponent
AbstractInputController --|> InputController
AbstractAIInputController --|> AbstractInputController
KeyboardInputController --|> AbstractInputController
ChasingAIInputController --|> AbstractAIInputController
MosquitoAIInputController --|> AbstractAIInputController

%%
class InputComponent {
    <<interface>>
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
    +getCommandsQueue(): Queue~Command~
}
%%
class AbstractAIInputController {
    <<abstract>>
    #computeNextCommand(): void
    +getCommandsQueue(): Queue~Command~
}
%%
class KeyboardInputController {
    +notifyKeyPressed(int): void
    +notifyKeyReleased(int): void
}
%%
class ChasingAIInputController {
    #computeNextCommand(): void
}
%%
class MosquitoAIInputController {
    #computeNextCommand(): void
}