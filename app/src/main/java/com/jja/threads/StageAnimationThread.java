package com.jja.threads;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.jja.spacegame.StageView;

/**
 * Created by Julius on 25.07.2016.
 */
// Thread um die Animation zu steuern
public class StageAnimationThread extends Thread {

    public boolean stop = false; // boolean um festzustellen ob die Schleife weiter durchlaufen werden soll
    private SurfaceHolder surfaceHolder;
    private StageView sV;

    // constructor
    public StageAnimationThread(SurfaceHolder surfaceHolder, StageView stageView) {
        this.surfaceHolder = surfaceHolder;
        this.sV = stageView;
    }

    // methode die vom Thread durchgeführt wird ( mit Thread.start() oben )
    @SuppressLint("WrongCall")  // wird gebraucht damit kein fehler kommt ( eigentlich darf nur das Laufzeitsystem die Methode onDraw() aufrufen )
    public void run() {
        while (!stop) {

            // hier neue x und y positionen der Gegner usw berechnen
            // zB so


            Canvas c = null;
            try {
                c = surfaceHolder.lockCanvas(null); // "Start editing the pixels in the surface."
                synchronized (surfaceHolder) {
                    if (c!=null)
                        // SpacegameView neu zeichnen
                        sV.onDraw(c);
                }
            } finally {
                if (c != null) surfaceHolder.unlockCanvasAndPost(c); // "Finish editing pixels in the surface."
            }
        }
    }
}
