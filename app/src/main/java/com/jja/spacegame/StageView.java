package com.jja.spacegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.jja.data.DamageModule;
import com.jja.data.HealthModule;
import com.jja.data.Module;
import com.jja.data.SpeedModule;
import com.jja.threads.StageAnimationThread;

import java.util.ArrayList;
import java.util.List;

import com.jja.data.Enemy;
import com.jja.data.Player;
import com.jja.data.Projectile;

public class StageView extends SurfaceView implements SurfaceHolder.Callback {

    // thread um die animationen zu steuern
    private StageAnimationThread saThread = null;

    private int width;
    private int height;

    // unsere "spielfiguren"
    private Player myPlayer;
    private List<Projectile> playerProjectiles;
    private List<Projectile> enemyProjectiles;
    private List<Enemy> enemies;


    // Bitmaps
    private SpaceshipBitmap mySpaceship;
    private Bitmap bm_spaceship;

    public StageView(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
        getHolder().addCallback(this);

        // Bildschirmgröße ermitteln
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.width = size.x;
        this.height = size.y;

        ArrayList<Module> playerModules = new ArrayList<Module>();
        for(int i = 0; i < 5; i++)
        {
            playerModules.add(i, new HealthModule(i, 50));
        }
        myPlayer = new Player (100, 100, 100, 20, 40, playerModules);
        playerProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(new Enemy(0, 100, 400, 100, 20, 30));

        mySpaceship = new SpaceshipBitmap(getContext(),R.drawable.small_spaceship_hull1,
                R.drawable.small_spaceship_wings1,
                R.drawable.small_spaceship_cockpit1,
                R.drawable.small_spaceship_drive1,
                R.drawable.small_spaceship_canons1);

        bm_spaceship  = Bitmap.createScaledBitmap(mySpaceship.getTogether(), mySpaceship.getWidth()*width/1440, mySpaceship.getHeight()*height/2560, false);

    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Projectile> getPlayerProjectiles() {
        return playerProjectiles;
    }

    public List<Projectile> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    // wird vom AnimationsThread aufgerufen um das naechste Bild zu zeichnen
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Farbe mit der gezeichnet wird
        Paint paint = new Paint();

        // Bildschirm auf komplett schwarz
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        // Spieler als Kreis zeichnen
        paint.setColor(Color.GREEN);
        //canvas.drawCircle(myPlayer.getPosX(), myPlayer.getPosY(), myPlayer.getHitboxradius(), paint);
        canvas.drawBitmap(bm_spaceship, myPlayer.getPosX() - bm_spaceship.getWidth()/2, myPlayer.getPosY() - bm_spaceship.getHeight()/2,null);

        // alle Gegner zeichnen
        paint.setColor(Color.MAGENTA);
        for(Enemy e : enemies)
        {
            canvas.drawCircle(e.getPosX(), e.getPosY(), e.getHitboxradius(), paint);
        }

        // Projektile der Gegner zeichnen
        paint.setColor(Color.RED);
        for(Projectile p : enemyProjectiles)
        {
            canvas.drawCircle(p.getPosX(), p.getPosY(), 5, paint);
        }

        // Projektile des Spielers zeichnen
        paint.setColor(Color.BLUE);
        for(Projectile p : playerProjectiles)
        {
            canvas.drawCircle(p.getPosX(), p.getPosY(), 5, paint);
        }
    }

    public boolean onTouchEvent (MotionEvent event) {
        int newPosX = (int) event.getX();
        int newPosY = (int) event.getY();
        int curPosX = myPlayer.getPosX();
        int curPosY = myPlayer.getPosY();
        int deltaPosX = newPosX - curPosX;
        int deltaPosY = newPosY - curPosY;

        if (event.getAction()== MotionEvent.ACTION_DOWN) {
            myPlayer.setPosX(curPosX + (int)myPlayer.getSpeedBoost() * deltaPosX/10);
            myPlayer.setPosY(curPosY + (int)myPlayer.getSpeedBoost() * deltaPosY/10);
        }
        if (event.getAction()== MotionEvent.ACTION_MOVE) {
            myPlayer.setPosX(curPosX + (int)myPlayer.getSpeedBoost() * deltaPosX/10);
            myPlayer.setPosY(curPosY + (int)myPlayer.getSpeedBoost() * deltaPosY/10);
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
