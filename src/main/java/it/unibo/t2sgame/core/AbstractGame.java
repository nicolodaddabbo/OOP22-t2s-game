package it.unibo.t2sgame.core;

import it.unibo.t2sgame.view.api.GameScene;

public class AbstractGame {
    /**
     * The enviroment where the game's entitites will be stored
     */
    private final Enviroment enviroment;
    /**
     * The game's logics and state
     */
    private final State state;

    public AbstractGame(Enviroment enviroment, State state, GameScene scene) {
        this.enviroment = enviroment;
        this.state = state;
        // Setting the scene to the engine
        this.enviroment.setScene(scene);
        // Setting the enviroment to the scene
        // scene.setEnviroment(this.enviroment);
    }

    /**
     * Starting the game loop
     */
    public void start(){
        while(!this.state.isOver()){
            this.enviroment.update();
        }
    }

}
