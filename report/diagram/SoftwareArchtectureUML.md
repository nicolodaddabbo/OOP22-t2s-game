```mermaid
classDiagram

GameEngine --o Game 
GameEngine --> Scene
Game --o  State
Game --o World
World --* GameObject
GameObject --> GameComponent
GameComponent <|-- GraphicsComponent
Scene --> Graphics
GraphicsComponent <-- GameObject
Graphics <-- GraphicsComponent

%% Rapresenting the MVC's "Model"
%% Every thing in the model is a GameObject
class GameObject{
    <<interface>>
    getInputController() Optional~InputController~
    getGameComponents() List~GameComponents~
    getGraphicsComponent() Optional~GraphicsComponent~
    update() void
    render() void
    notifyComponent() void

}
%%
class GameComponent{
    <<interface>>
    update(GameObject object);
}
%%
%%
class GraphicsComponent{
    <<interface>>
    update(GameObject object) void
}
%%
%% Rapresenting the MVC's "Controller"
class GameEngine{
    <<interface>>
    run() 
}
%% Rapresenting the current game of the player
class Game{
    <<interface>>
    getState() State
    getWorld() World
}
%% State of a Game
class State{
    <<interface>>
    getScore() int
    isOver() boolean
}
%% Basically a "Container" for all GameObjects
class World{
    <<interface>>
    getGameObject() List of GameObject
    updateWorld() void
}
%% Rapresenting the MVC's "View" 
class Scene{
    <<interface>>
    render() void
}
%% 
class Graphics{
    <<interface>>
    drawPlayer(GameObject object) void
    drawEnemies(GameObject object) void
}
%% 


```