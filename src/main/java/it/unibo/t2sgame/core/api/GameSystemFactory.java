package it.unibo.t2sgame.core.api;

public interface GameSystemFactory {
    
    GameSystem craeteInputSystem();

    GameSystem createPhysicsSystem();

    GameSystem createCollisionSystem();
    
    GameSystem createGraphicsSystem();

}
