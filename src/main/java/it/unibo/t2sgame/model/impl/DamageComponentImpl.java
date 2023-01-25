package it.unibo.t2sgame.model.impl;

import it.unibo.t2sgame.common.StopWatch;
import it.unibo.t2sgame.model.api.DamageComponent;
import it.unibo.t2sgame.model.api.Entity;
import it.unibo.t2sgame.model.api.Message;

public class DamageComponentImpl implements DamageComponent {

    private Entity entity;
    private int damage;
    private double cooldown;
    private final StopWatch timer = new StopWatch().start();

    public DamageComponentImpl(int damage, double cooldown) {
        this.damage = damage;
        this.cooldown = cooldown;
    }

    @Override
    public void update() {
        
    }

    @Override
    public <T> void receive(Message<T> message) {
        
    }

    @Override
    public boolean canDamage() {
        if(this.timer.getElapsedSeconds() >= this.cooldown){
            this.timer.restart();
            return true;
        }
        return false;
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}
