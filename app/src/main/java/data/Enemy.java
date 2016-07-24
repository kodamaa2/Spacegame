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

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getHitboxradius() {
        return hitboxradius;
    }

    public void setHitboxradius(double hitboxradius) {
        this.hitboxradius = hitboxradius;
    }

    public double getFirerate() {
        return firerate;
    }

    public void setFirerate(double firerate) {
        this.firerate = firerate;
    }
}
