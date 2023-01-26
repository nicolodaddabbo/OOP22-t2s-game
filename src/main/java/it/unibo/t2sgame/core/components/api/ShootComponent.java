package it.unibo.t2sgame.core.components.api;

public interface ShootComponent extends Component {
    
    /**
     * 
     * @return the firerate if the entity
     */
    double getFireRate();
    /**
     * 
     * @param firerate the firerate to set
     */
    void setFireRate(double fireRate);

}
