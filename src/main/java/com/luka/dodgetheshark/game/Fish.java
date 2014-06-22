package com.luka.dodgetheshark.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Luka on 18/06/14.
 */
public abstract class Fish {

    public int x;
    public int y;
    public Bitmap image;

    public Fish(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Canvas draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,null);
        return canvas;
    }

}
