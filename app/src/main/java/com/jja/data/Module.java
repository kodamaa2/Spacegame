package com.jja.data;

/**
 * Created by Julius on 25.07.2016.
 */
public class Module {
    private int mid, damage, damagetype;
    private double speedBoost;

    public Module(int id){
        this.mid = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDamagetype(int damagetype) {
        this.damagetype = damagetype;
    }
    public double getSpeedBoost() {
        return speedBoost;
    }

    public void setSpeedBoost(double speedBoost) {
        this.speedBoost = speedBoost;
    }
}
