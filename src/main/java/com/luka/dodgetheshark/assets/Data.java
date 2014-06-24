package com.luka.dodgetheshark.assets;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Paint;

import com.luka.dodgetheshark.game.SoundController;

/**
 * Created by Luka on 24/06/14.
 */
public class Data {

    public static int score;
    public static SoundController controller;

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
        public static AssetFileDescriptor lwoo;
        public static AssetFileDescriptor hwoo;
    }

    public static class Calculations {
        public static int highscoreX;
        public static int highscoreY;
        public static int highscoreHalfX;
        public static int highscoreHalfY;
        public static int highscoreHalfH;
        public static int highscoreFy;
        public static int highscoreXtwenty;
        public static int highScoreNewX;
        public static int highscoreHalfYEighty;
        public static int readyX;
        public static int readyY;
        public static int widthFifty;
        public static int headerX;
        public static int headerY;

    }

    public static class Constants {
        public static final String highscore = "Highscore";
        public static final String score = "Score ";
    }


}
