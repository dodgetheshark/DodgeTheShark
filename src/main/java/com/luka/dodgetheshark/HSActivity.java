package com.luka.dodgetheshark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Data;
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
    private String[] scoresAsStrings;
    private String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        createPrefs();
        this.highscores = getHighscores();
        updateHighScores(Data.score);
        this.highscore = Data.Assets.highscore;
        this.play = new Button(Data.Calculations.highscoreX , (Data.Calculations.highscoreY + highscore.getHeight()+20), Data.Assets.play);
        this.back = new Button((Data.Calculations.highscoreX +Data.Assets.highscore.getWidth()-Data.Assets.back.getWidth()) , (Data.Calculations.highscoreY + highscore.getHeight()+20), Data.Assets.back);
      /*  Rect bounds = new Rect();
        Data.Assets.textPaint.getTextBounds(highText,0,highText.length(),bounds);
        this.hy = y+ bounds.height() + 50 ;
        this.halfH = highscore.getWidth()/2;
        this.hx = (int) (Data.Calculations.highscoreX  + halfH - Data.Assets.textPaint.measureText(highText)/2);
        this.fy = y+50;*/
        setScores();
        score = new StringBuilder().append(Data.Constants.score).append(Data.score).toString();
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

    private void setScores() {
        scoresAsStrings = new String[3];
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < 3 ; i++) {
            scoresAsStrings[i] = builder.append(i+1).append(") ").append(highscores[i]).toString();
            builder.setLength(0);
        }
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
        canvas.drawBitmap(highscore, Data.Calculations.highscoreX, Data.Calculations.highscoreY, null);
       canvas.drawText(Data.Constants.highscore,Data.Calculations.highscoreHalfX , Data.Calculations.highscoreFy ,Data.Assets.textPaint);
        for(int i = 0 ; i <3 ;i++) {
            canvas.drawText(scoresAsStrings[i], Data.Calculations.highscoreXtwenty ,Data.Calculations.highscoreHalfY+(i*80),Data.Assets.textPaint);
        }
        canvas.drawText(score, Data.Calculations.highScoreNewX, Data.Calculations.highscoreHalfYEighty, Data.Assets.textPaint);
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
