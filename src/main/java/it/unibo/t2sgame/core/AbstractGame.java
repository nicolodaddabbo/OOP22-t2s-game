package it.unibo.t2sgame.core;


public abstract class AbstractGame {
    /**
     * The enviroment where the game's entitites will be stored
     */
    private final Enviroment enviroment;
    /**
     * The game's logics and state
     */
    private final State state;
    /**
     * Create a new game with the given {@link enviroment} and {@link state}
     * @param enviroment 
     * @param state
     */
    public AbstractGame(Enviroment enviroment, State state) {
        this.enviroment = enviroment;
        this.state = state;
        // Setting the scene to the engine
        // Setting the enviroment to the scene
        // scene.setEnviroment(this.enviroment);
    }

    /**
     * Starting the game loop
     * Start is a template method which depends on this.checkLogics() implementation
     */
    public void start(){
        while(!this.state.isOver()){
            this.checkLogics();
            this.enviroment.update();
        }
    }
    /**
     * Abstract method which has to be ovveride in order
     * to check the logics of the current game.
     */
    abstract public void checkLogics();

}
