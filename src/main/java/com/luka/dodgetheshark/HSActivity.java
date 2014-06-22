package com.luka.dodgetheshark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Assets;
import com.luka.dodgetheshark.assets.Statics;
import com.luka.dodgetheshark.ui.Button;

/**
 * Created by Luka on 21/06/14.
 */
public class HSActivity extends AbstractActivity {

    private final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences preferences;
    private int[] highscores;
    private Bitmap highscore;
    private Button play;
    private Button back;
    private int x;
    private int hx;
    private int hy;
    private int fy;
    private int halfH;
    private int y;
    private final String highText = "Highscores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        createPrefs();
        this.highscores = getHighscores();
        updateHighScores(Statics.score);
        this.highscore = Assets.highscore;
        this.x = (width/2-(highscore.getWidth()/2));
        this.y = (height/2-(highscore.getHeight()/2));
        this.play = new Button(x , (y+highscore.getHeight()+20), Assets.play);
        this.back = new Button((x+Assets.highscore.getWidth()-Assets.back.getWidth()) , (y+highscore.getHeight()+20), Assets.back);
        Rect bounds = new Rect();
        Assets.textPaint.getTextBounds(highText,0,highText.length(),bounds);
        this.hy = y+ bounds.height() + 50 ;
        this.halfH = highscore.getWidth()/2;
        this.hx = (int) ( x + halfH - Assets.textPaint.measureText(highText)/2);
        this.fy = y+50;


    }

    private void createPrefs() {
        StringBuilder builder = new StringBuilder();
        SharedPreferences.Editor editor = preferences.edit();
        for(int i = 1 ; i < 4 ; i++) {
            if(!preferences.contains(builder.append("score").append(i).toString())) {
                editor.putInt(builder.toString(),0);
                builder = new StringBuilder();
            }
        }
        editor.commit();
    }

    private int[] getHighscores(){
        StringBuilder builder = new StringBuilder();
        int[] highscores = new int[3];
        for(int i = 1 ; i < 4 ; i++) {
            builder.append("score").append(i);
            highscores[i-1] = preferences.getInt(builder.toString(),0);
            builder = new StringBuilder();
        }
        return highscores;
    }

    private void updateHighScores(int score) {
        for(int i = 0 ; i < 3 ; i++) {
            if(highscores[i] < score) {
                for (int j = 2; j > i; j--) {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = score;
                break;
            }
        }
        writeHighScores();
    }

    private void writeHighScores() {
        StringBuilder builder = new StringBuilder();
        SharedPreferences.Editor editor = preferences.edit();
        for(int i = 1 ; i < 4 ; i++) {
            editor.putInt(builder.append("score").append(i).toString(), highscores[i-1]);
            builder = new StringBuilder();
        }
        editor.commit();
    }

    @Override
    public Canvas draw(Canvas canvas) {
        canvas.drawBitmap(highscore,x,y,null);
     //   canvas.drawText("HighScores",hx , fy ,Assets.textPaint);
     /*   for(int i = 0 ; i <3 ;i++) {
            canvas.drawText((i+1) + ") " + String.valueOf(highscores[i]), x+20,hy+(i*80),Assets.textPaint);
        }*/
        canvas = play.draw(canvas);
        canvas = back.draw(canvas);
        return canvas;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        if(play.clicked(x,y)) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        } else if(back.clicked(x,y)) {
            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
            startActivity(intent);
        }

        return false;
    }
}
