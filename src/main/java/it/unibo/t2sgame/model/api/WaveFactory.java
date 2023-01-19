package it.unibo.t2sgame.model.api;

public interface WaveFactory {
    
    /**
     * 
     * @return a basic wave that depends on the state of the game
     */
    Wave createBasicWave();
}
