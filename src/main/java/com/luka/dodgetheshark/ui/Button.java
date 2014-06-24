package com.luka.dodgetheshark.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;

import com.luka.dodgetheshark.assets.Data;

/**
 * Created by Luka on 19/06/14.
 */
public class Button {

    private int x;
    private int y;
    private Bitmap image;
    private SoundPool pool;
    private int click;

    public Button(int x, int y, Bitmap image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        this.click =this.pool.load(Data.Assets.click , 1);
    }

    public Canvas draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,null);
        return canvas;
    }

    public boolean clicked(int mx, int my) {
        if(x-mx <= 0 && x-mx >= image.getWidth()*-1 && y-my <= 0 && y-my >= image.getHeight()*-1) {
            pool.play(click,1.0f, 1.0f, 0, 0, 1);
            return true;
        } else {
            return false;
        }
    }

}
