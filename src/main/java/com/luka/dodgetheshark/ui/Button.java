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
    private int click;

    public Button(int x, int y, Bitmap image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public Canvas draw(Canvas canvas) {
        canvas.drawBitmap(image,x,y,null);
        return canvas;
    }

    public boolean clicked(int mx, int my) {
        if(x-mx <= 0 && x-mx >= image.getWidth()*-1 && y-my <= 0 && y-my >= image.getHeight()*-1) {
            Data.controller.play(Data.controller.click);
            return true;
        } else {
            return false;
        }
    }

}
