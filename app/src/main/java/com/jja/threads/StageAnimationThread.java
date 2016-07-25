package com.jja.threads;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;

import com.jja.data.Enemy;
import com.jja.data.Player;
import com.jja.data.Projectile;
import com.jja.spacegame.StageView;

import java.util.Calendar;

/**
 * Created by Julius on 25.07.2016.
 */
// Thread um die Animation zu steuern
public class StageAnimationThread extends Thread {

    public boolean stop = false; // boolean um festzustellen ob die Schleife weiter durchlaufen werden soll
    private SurfaceHolder surfaceHolder;
    private StageView stageView;
    private long timecount;
    private boolean back = false;

    // constructor
    public StageAnimationThread(SurfaceHolder surfaceHolder, StageView stageView) {
        this.surfaceHolder = surfaceHolder;
        this.stageView = stageView;
        this.timecount = 0;
    }

    // methode die vom Thread durchgeführt wird ( mit Thread.start() oben )
    @SuppressLint("WrongCall")  // wird gebraucht damit kein fehler kommt ( eigentlich darf nur das Laufzeitsystem die Methode onDraw() aufrufen )
    public void run() {
        while (!stop) {

            // hier neue x und y positionen der Gegner usw berechnen
            // zB so

            /*for(Enemy e : stageView.getEnemies()){
                if(!back)
                {
                    e.move(10,0);
                    if(e.getPosX() == stageView.getWidth())
                        back = true;
                }
                else
                {
                    e.move(-10, 0);
                    if(e.getPosX() == 0)
                        back = false;
                }
            }*/

            // Bewegt alle Projektile entsprechend ihrer Richtung und Geschwindigkeit weiter
            for(int i = 0; i <  stageView.getPlayerProjectiles().size(); i++){
                Projectile p = stageView.getPlayerProjectiles().get(i);
                p.move(p.getDirX() * p.getSpeed(), p.getDirY() * p.getSpeed());
                if(p.getPosX() < 0 || p.getPosY() < 0) {
                    stageView.getPlayerProjectiles().remove(i);
                    i--;
                }
            }
            for(int i = 0; i <  stageView.getEnemyProjectiles().size(); i++){
                Projectile p = stageView.getEnemyProjectiles().get(i);
                p.move(p.getDirX() * p.getSpeed(), p.getDirY() * p.getSpeed());
                if(p.getPosX() < 0 || p.getPosY() < 0) {
                    stageView.getEnemyProjectiles().remove(i);
                    i--;
                }
            }


            // Hier Kollisionen berechnen
            for(int i = 0; i < stageView.getPlayerProjectiles().size(); i++){
                Projectile p = stageView.getPlayerProjectiles().get(i);
                for(Enemy e : stageView.getEnemies()){
                    if(Math.pow((p.getPosX() - e.getPosX()), 2) + Math.pow((p.getPosY() - e.getPosY()), 2) < (Math.pow(e.getHitboxradius(), 2))){
                        e.updateHealth(-p.getDamage());
                        if(e.getHealth() <= 0)
                            stageView.getEnemies().remove(e);
                        stageView.getPlayerProjectiles().remove(i);
                        i--;
                        break;
                    }
                }
            }

            Player player = stageView.getMyPlayer();
            for(int i = 0; i < stageView.getEnemyProjectiles().size(); i++){
                Projectile p = stageView.getEnemyProjectiles().get(i);
                if(Math.pow((p.getPosX() - player.getPosX()), 2) + Math.pow((p.getPosY() - player.getPosY()), 2) < (Math.pow(player.getHitboxradius(), 2))){
                   player.updateHealth(-p.getDamage());
                    if(player.getHealth() <= 0)
                        System.out.println("You are dead!");
                    stageView.getEnemyProjectiles().remove(i);
                    i--;
                    break;
                }
            }


            // Hier neue Projektile erstellen
            timecount++;
            // Projektile des Spielers erstellen
            if(timecount % (player.getFirerate()/2) == 0){
                stageView.getPlayerProjectiles().add(stageView.getPlayerProjectiles().size(), new Projectile(0, player.getPosX(), player.getPosY(), 0, -1, 30, player.getDamage()));
            }

            // Projektile aller Gegner erstellen
            for(Enemy e : stageView.getEnemies()){
                if(timecount % (e.getFirerate()/2) == 0)
                    stageView.getEnemyProjectiles().add(stageView.getEnemyProjectiles().size(), new Projectile(0, e.getPosX(), e.getPosY(), 0, 1, 3, 10));
            }

            Canvas c = null;
            try {
                c = surfaceHolder.lockCanvas(null); // "Start editing the pixels in the surface."
                synchronized (surfaceHolder) {
                    if (c!=null)
                        // SpacegameView neu zeichnen
                        stageView.onDraw(c);
                }
            } finally {
                if (c != null) surfaceHolder.unlockCanvasAndPost(c); // "Finish editing pixels in the surface."
            }
        }
    }
}
