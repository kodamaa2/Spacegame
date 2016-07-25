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

            for(Projectile p : stageView.getPlayerProjectiles()){
                for(Enemy e : stageView.getEnemies()){
                    if(Math.pow((p.getPosX() - e.getPosX()), 2) + Math.pow((p.getPosY() - e.getPosY()), 2) < (Math.pow(e.getHitboxradius(), 2))){
                        e.updateHealth(-p.getDamage());
                        //stageView.getPlayerProjectiles().remove(p);
                        if(e.getHealth() < 0)
                            stageView.getEnemies().remove(e);
                    }
                }
            }

            Player player = stageView.getMyPlayer();
            for(Projectile p: stageView.getEnemyProjectiles()){
                if(Math.pow((p.getPosX() - player.getPosX()), 2) + Math.pow((p.getPosY() - player.getPosY()), 2) < (Math.pow(player.getHitboxradius(), 2))){
                   player.updateHealth(-p.getDamage());
                    //stageView.getEnemyProjectiles().remove(p);
                    if(player.getHealth() < 0)
                        System.out.println("You are dead!");
                }
            }


            // Hier neue Projektile erstellen
            timecount++;
            // Projektile des Spielers erstellen
            if(timecount % (stageView.getMyPlayer().getFirerate()) == 0){
                stageView.getPlayerProjectiles().add(stageView.getPlayerProjectiles().size(), new Projectile(0, stageView.getMyPlayer().getPosX(), stageView.getMyPlayer().getPosY(), 0, -1, 12, 10));
            }

            // Projektile aller Gegner erstellen
            for(Enemy e : stageView.getEnemies()){
                if(timecount % e.getFirerate() == 0)
                    stageView.getEnemyProjectiles().add(stageView.getEnemyProjectiles().size(), new Projectile(0, e.getPosX(), e.getPosY(), 0, 1, 15, 10));
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
