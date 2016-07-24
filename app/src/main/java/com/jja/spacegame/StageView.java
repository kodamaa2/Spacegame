package com.jja.spacegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jja.threads.StageAnimationThread;

import java.util.ArrayList;
import java.util.List;

import data.Enemy;
import data.Player;
import data.Projectile;

public class StageView extends SurfaceView implements SurfaceHolder.Callback {

    // thread um die animationen zu steuern
    private StageAnimationThread saThread = null;
    // unsere "spielfiguren"
    private Player myPlayer;
    private List<Projectile> playerProjectiles;
    private List<Projectile> enemyProjectiles;
    private List<Enemy> enemies;


    public StageView(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        getHolder().addCallback(this);
        myPlayer = new Player (100, 100, 100, 20, 15);
        playerProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
        enemies = new ArrayList<>();
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

    public boolean onTouchEvent (MotionEvent event) {
        int newPosX = (int) event.getX();
        int newPosY = (int) event.getY();
        int curPosX = myPlayer.getPosX();
        int curPosY = myPlayer.getPosY();
        int deltaPosX = newPosX - curPosX;
        int deltaPosY = newPosY - curPosY;

        if (event.getAction()== MotionEvent.ACTION_DOWN) {
            myPlayer.setPosX(curPosX + deltaPosX/2);
            myPlayer.setPosY(curPosY + deltaPosY/2);
        }
        if (event.getAction()== MotionEvent.ACTION_MOVE) {
            myPlayer.setPosX(curPosX + deltaPosX/2);
            myPlayer.setPosY(curPosY + deltaPosY/2);
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (saThread!=null) return;
        saThread = new StageAnimationThread(getHolder(), this);
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


}