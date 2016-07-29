package com.jja.spacegame;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Andreas on 29.07.2016.
 */
public class SpaceshipBitmap {

    private Bitmap hull;
    private Bitmap wings;
    private Bitmap cockpit;
    private Bitmap drive;
    private Bitmap canons;
    private Bitmap together;

    private int width;
    private int height;

    private Resources res; //res ordner

    public SpaceshipBitmap(Context current, int hullResource, int wingsResource ,int cockpitResource, int driveResource, int canonsResource) {
        this.res = current.getResources(); //wegen nicht activity-Klasse, Kontext übergeben

        this.hull = BitmapFactory.decodeResource(res, hullResource);
        this.wings = BitmapFactory.decodeResource(res,wingsResource);
        this.cockpit = BitmapFactory.decodeResource(res,cockpitResource);
        this.drive = BitmapFactory.decodeResource(res,driveResource);
        this.canons = BitmapFactory.decodeResource(res,canonsResource);

        System.out.println("hull:" + hull.getWidth() + "." + hull.getHeight());

        Bitmap together = hull.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(together);

        c.drawBitmap(wings, 0, 0, null);
        c.drawBitmap(cockpit, 0, 0, null);
        c.drawBitmap(drive, 0, 0, null);
        c.drawBitmap(canons, 0, 0, null);

        this.together = together;
        this.width = together.getWidth();
        this.height = together.getHeight();
    }

    public Bitmap getHull() {
        return hull;
    }

    public void setHull(Bitmap hull) {
        this.hull = hull;
    }

    public Bitmap getWings() {
        return wings;
    }

    public void setWings(Bitmap wings) {
        this.wings = wings;
    }

    public Bitmap getCockpit() {
        return cockpit;
    }

    public void setCockpit(Bitmap cockpit) {
        this.cockpit = cockpit;
    }

    public Bitmap getDrive() {
        return drive;
    }

    public void setDrive(Bitmap drive) {
        this.drive = drive;
    }

    public Bitmap getCanons() {
        return canons;
    }

    public void setCanons(Bitmap canons) {
        this.canons = canons;
    }

    public Bitmap getTogether() {
        return together;
    }

    public void setTogether(Bitmap together) {
        this.together = together;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
