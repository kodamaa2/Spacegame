package com.jja.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julius on 24.07.2016.
 */
public class Player {
    private int health, posX, posY, damage;

    private double speedBoost;
    private float hitboxradius, firerate;
    private List<Module> moduleList;

    public Player(int health, int posX, int posY, float hitboxradius, float firerate, ArrayList<Module> mdlList) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.hitboxradius = hitboxradius;
        this.firerate = firerate;
        this.speedBoost = 1;
        this.damage = 10;
        this.moduleList = mdlList;
        this.processModules();

    }

    private void processModules() {
        for(Module m : moduleList){
            if(m instanceof DamageModule)
            {
                this.damage += m.getDamage();
            }
            else if(m instanceof SpeedModule)
            {
                this.speedBoost *= m.getSpeedBoost();
            }
            else if(m instanceof HealthModule)
            {
                this.health += m.getHealthPoints();
            }
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public double getSpeedBoost() {
        return speedBoost;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public float getHitboxradius() {
        return hitboxradius;
    }

    public void setHitboxradius(float hitboxradius) {
        this.hitboxradius = hitboxradius;
    }

    public float getFirerate() {
        return firerate;
    }

    public void setFirerate(float firerate) {
        this.firerate = firerate;
    }

    public Projectile shoot(int typ){
        return new Projectile(typ, posX, posY, 1, 1, 1, 1);
    }

    public void move(int deltax, int deltay){
        this.posX += deltax;
        this.posY += deltay;
    }

    public void updateHealth(int deltaHealth){
        this.health += deltaHealth;
    }
}
