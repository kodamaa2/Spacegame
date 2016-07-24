package com.jja.data;

/**
 * Created by Julius on 24.07.2016.
 */
public class Projectile {
    private int pid, posX, posY, dirX, dirY, speed, damage;

    public Projectile(int id, int x, int y, int dx, int dy, int spd, int dmg){
        this.pid = id;
        this.posX = x;
        this.posY = y;
        this.dirX = dx;
        this.dirY = dy;
        this.speed = spd;
        this.damage = dmg;
    }

    public void move(int deltax, int deltay){
        this.posX += deltax;
        this.posY += deltay;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
