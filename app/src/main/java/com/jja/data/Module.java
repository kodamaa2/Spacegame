package com.jja.data;

/**
 * Created by Julius on 25.07.2016.
 */
public class Module {
    //Attribute
    private int mid, damage, damagetype, health;
    private double speedBoost;

    // Konstruktor
    public Module(int id){
        this.mid = id;
    }

    // Getter und Setter
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
    public void setHealthPoints(int health) {
        this.health = health;
    }
    public int getHealthPoints() {
        return health;
    }
}
