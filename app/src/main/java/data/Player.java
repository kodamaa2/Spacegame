package data;

/**
 * Created by Julius on 24.07.2016.
 */
public class Player {
    int health, posX, posY;
    double hitboxradius, firerate;

    public Player(int health, int posX, int posY, double hitboxradius, double firerate) {
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
