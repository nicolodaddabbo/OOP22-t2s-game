```mermaid
classDiagram

EntityFactory <|-- EntityFactoryImpl
Entity <|-- EntityImpl
EntityFactoryImpl --> EntityImpl

class EntityFactory {
    <<Interface>>
    createPlayer(Vector2D postion) Entity
    createEnemy(Vector2D position) Entity
    createWall(Vector2D position, Double width, Double height) Entity
}

class EntityFactoryImpl {
    <<>>
}

class Entity {
    <<Interface>>
}

class EntityImpl {
    <<>>
}

```