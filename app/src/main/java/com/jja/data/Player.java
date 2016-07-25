package com.jja.data;

/**
 * Created by Julius on 24.07.2016.
 */
public class Player {
    private int health, posX, posY;
    private float hitboxradius, firerate;

    public Player(int health, int posX, int posY, float hitboxradius, float firerate) {
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.hitboxradius = hitboxradius;
        this.firerate = firerate;
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
