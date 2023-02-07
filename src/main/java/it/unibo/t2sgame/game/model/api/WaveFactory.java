package it.unibo.t2sgame.game.model.api;

/**
 * an interface representing a factory of waves.
 */
public interface WaveFactory {
    /**
     * 
     * @param round in which the method is called
     * @return a wave filled with only Basic enemies
     */
    Wave createBasicWave(int round);
    /**
     * 
     * @param round in which the method is called
     * @return a wave filled with random enemies
     */
    Wave createRandomWave(int round);
    /**
     * 
     * @param round in which the method is called
     * @return a wave with a boss and some random enemies
     */
    Wave createBossWave(int round);
}
