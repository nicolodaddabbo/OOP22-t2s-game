package it.unibo.t2sgame.components.api;

public interface DamageComponent extends Component{
    /**
     * 
     * @return true if the cooldown period is over, otherwise returns false
     */
    boolean canDamage();
    /**
     * 
     * @return the damage if the entity
     */
    int getDamage();
    /**
     * 
     * @param damage the damage to set
     */
    void setDamage(int damage);

}
