```mermaid
classDiagram

InputController --* InputComponent
AbstractInputController --|> InputController
AbstractChasingAIInputController --|> AbstractInputController
KeyboardInputController --|> AbstractInputController
ChasingAIInputController --|> AbstractChasingAIInputController
MosquitoAIInputController --|> AbstractChasingAIInputController
GaussianAIInputController --|> AbstractChasingAIInputController

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
class AbstractChasingAIInputController {
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
%%
class GaussianAIInputController {
    #computeNextCommand(): void
}