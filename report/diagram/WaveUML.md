```mermaid
classDiagram
Wave <-- WaveFactory 

class Wave {
    <<interface>>
    +getEnemies(): List~Entity~ 
    +addEnemy(Entity entity): Wave
}


class WaveFactory {
    <<interface>>
    +createBasicWave(): Wave
    +createRandomWave():Wave
    +createBossWave(): Wave
}
```