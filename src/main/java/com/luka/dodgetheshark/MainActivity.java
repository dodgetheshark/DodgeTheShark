package com.luka.dodgetheshark;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Data;

import java.io.IOException;

public class MainActivity extends AbstractActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setAssets();
        super.onCreate(savedInstanceState);
        setCalculations();
    }

    @Override
    public Canvas draw(Canvas canvas) {
        return canvas;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    private void setAssets() {
        AssetManager manager = getAssets();
        try {
            Data.Assets.background = BitmapFactory.decodeStream(manager.open("background-repeat.png"));
            Data.Assets.fish = BitmapFactory.decodeStream(manager.open("fish.png"));
            Data.Assets.shark = BitmapFactory.decodeStream(manager.open("nshark.png"));
            Data.Assets.back = BitmapFactory.decodeStream(manager.open("back.png"));
            Data.Assets.deathspike = BitmapFactory.decodeStream(manager.open("deathspike.png"));
            Data.Assets.ideathspike = BitmapFactory.decodeStream(manager.open("deathspike2.png"));
            Data.Assets.scores = BitmapFactory.decodeStream(manager.open("scores.png"));
            Data.Assets.play = BitmapFactory.decodeStream(manager.open("play.png"));
            Data.Assets.highscore = BitmapFactory.decodeStream(manager.open("highscores.png"));
            Data.Assets.header = BitmapFactory.decodeStream(manager.open("headers.png"));
            Data.Assets.ready = BitmapFactory.decodeStream(manager.open("ready.png"));
            Data.Assets.pausedButton = BitmapFactory.decodeStream(manager.open("pause.png"));
            Data.Assets.pausedMemo = BitmapFactory.decodeStream(manager.open("gpaused.png"));
            Data.Assets.click = manager.openFd("click.ogg");
            Data.Assets.pop = manager.openFd("pop.ogg");
            Typeface typeface = Typeface.createFromAsset(getAssets(),"Harabara.ttf");
            Data.Assets.textPaint = new Paint();
            Data.Assets.textPaint.setTextSize(32);
            Data.Assets.textPaint.setColor(Color.BLUE);
            Data.Assets.textPaint.setTypeface(typeface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCalculations() {
        Data.Calculations.highscoreX = (width/2-(Data.Assets.highscore.getWidth()/2));
        Data.Calculations.highscoreY = (height/2-(Data.Assets.highscore.getHeight()/2));
    }
}
