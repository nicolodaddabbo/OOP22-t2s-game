```mermaid
classDiagram

    GameEngine --> RunnableGameLoop
    
    class GameEngine{
        <<interface>>
        run() void
        stop() void
    }

    class RunnableGameLoop{
        run() void
    } 


```