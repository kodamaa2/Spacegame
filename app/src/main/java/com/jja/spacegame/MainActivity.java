package com.jja.spacegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import data.Player;
import data.Projectile;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new StageView(this, null, 0));
    }

}

class StageView extends SurfaceView implements SurfaceHolder.Callback {

    private StageAnimationThread saThread = null; // thread um die animationen zu steuern
    private Player myPlayer;
    private List<Projectile> playerProjectiles;
    private List<Projectile> enemyProjectiles;


    public StageView(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        getHolder().addCallback(this);
        myPlayer = new Player (100, 100, 100, 20, 15);
        playerProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
    }


    // wird vom AnimationsThread aufgerufen um das naechste Bild zu zeichnen
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Farbe mit der gezeichnet wird
        Paint paint = new Paint();
        // Bildschirm auf komplett schwarz
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        // fürs erste nur Spieler als Kreis zeichnen
        paint.setColor(Color.GREEN);
        canvas.drawCircle(myPlayer.getPosX(), myPlayer.getPosY(), 20, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (saThread!=null) return;
        saThread = new StageAnimationThread(getHolder());
        saThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // nichts passiert
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        saThread.stop = true;
    }

    // Thread um die Animation zu steuern
    private class StageAnimationThread extends Thread {

        public boolean stop = false; // boolean um festzustellen ob die Schleife weiter durchlaufen werden soll
        private SurfaceHolder surfaceHolder;

        // constructor
        public StageAnimationThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        // methode die vom Thread durchgeführt wird ( mit Thread.start() oben )
        @SuppressLint("WrongCall")  // wird gebraucht damit kein fehler kommt ( eigentlich darf nur das Laufzeitsystem die Methode onDraw() aufrufen )
        public void run() {
            while (!stop) {

                // hier neue x und y positionen der Gegner usw berechnen
                // zB so
                if( myPlayer.getPosX() < getHeight()) {
                    myPlayer.setPosX(myPlayer.getPosX()+1);
                }

                Canvas c = null;
                try {
                    c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        if (c!=null)
                            // SpacegameView neu zeichnen
                            onDraw(c);
                    }
                } finally {
                    if (c != null) surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}