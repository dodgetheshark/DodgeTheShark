package com.luka.dodgetheshark.assets;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Paint;

/**
 * Created by Luka on 24/06/14.
 */
public class Data {

    public static int score;

    public static class Assets {
        public static Bitmap back;
        public static Bitmap background;
        public static Bitmap deathspike;
        public static Bitmap ideathspike;
        public static Bitmap fish;
        public static Bitmap shark;
        public static Bitmap highscore;
        public static Bitmap play;
        public static Bitmap scores;
        public static Bitmap header;
        public static Bitmap ready;
        public static Bitmap pausedButton;
        public static Bitmap pausedMemo;
        public static Paint textPaint;
        public static AssetFileDescriptor click;
        public static AssetFileDescriptor pop;
    }

    public static class Calculations {
        public static int highscoreX;
        public static int highscoreY;
    }



}