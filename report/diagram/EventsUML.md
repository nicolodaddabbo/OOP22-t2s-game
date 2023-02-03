```mermaid
classDiagram

EventFactory --> Event
ShootComponent *-- EventFactory
note for ShootComponent "this.world.notifyEvent(this.eventFactory.onShoot(this.entity))"
ShootComponent --> World

class Event {
    <<interface>>
    +execute(World): void
}
%%
class EventFactory {
    <<interface>>
    +onShootEvent(Entity, Directions): Event
    +onDeathEvent(Entity): Event
}
%%
class World {
    <<interface>>
    +notifyEvent(Event): void
    +handleEvents(): void
}
%%
class ShootComponent {
    -shoot(): void
}