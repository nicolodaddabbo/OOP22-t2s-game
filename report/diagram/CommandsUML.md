```mermaid
classDiagram

Command --* InputController
Move --|> Command
Shoot --|> Command

class InputController {
    <<interface>>
    +getCommandsQueue(): Queue~Command~
}
%%
class Command {
    <<interface>>
    +execute(Entity): void
}
%%
class Move {
    +execute(Entity): void
}
%%
class Shoot {
    +execute(Entity): void
}