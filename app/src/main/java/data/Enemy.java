package data;

/**
 * Created by Julius on 24.07.2016.
 */
public class Enemy {

    int eid, health, posX, posY;
    double hitboxradius, firerate;

    public Enemy(int id, int hp, int x, int y, double rad, double frate){
        this.eid = id;
        this.health = hp;
        this.posX = x;
        this.posY = y;
        this.hitboxradius = rad;
        this.firerate = frate;
    }

    public Projectile shoot(Projectile typ){

    }

    public void move(int deltax, int deltay){
        this.posX += deltax;
        this.posY += deltay;
    }

    public void updateHealth(int deltaHealth){
        this.health += deltaHealth;
    }
}
