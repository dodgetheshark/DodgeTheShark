package com.luka.dodgetheshark;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.View;
import android.view.View.OnTouchListener;

import com.luka.dodgetheshark.assets.Assets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AbstractActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setAssets();
        super.onCreate(savedInstanceState);
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
            Assets.background = BitmapFactory.decodeStream(manager.open("background-repeat.png"));
            Assets.fish = BitmapFactory.decodeStream(manager.open("fish.png"));
            Assets.shark = BitmapFactory.decodeStream(manager.open("nshark.png"));
            Assets.back = BitmapFactory.decodeStream(manager.open("back.png"));
            Assets.deathspike = BitmapFactory.decodeStream(manager.open("deathspike.png"));
            Assets.ideathspike = BitmapFactory.decodeStream(manager.open("deathspike2.png"));
            Assets.scores = BitmapFactory.decodeStream(manager.open("scores.png"));
            Assets.play = BitmapFactory.decodeStream(manager.open("play.png"));
            Assets.highscore = BitmapFactory.decodeStream(manager.open("highscores.png"));
            Assets.header = BitmapFactory.decodeStream(manager.open("headers.png"));
            Assets.ready = BitmapFactory.decodeStream(manager.open("ready.png"));
            Assets.click = manager.openFd("click.ogg");
            Assets.pop = manager.openFd("pop.ogg");
            Typeface typeface = Typeface.createFromAsset(getAssets(),"Harabara.ttf");
            Assets.textPaint = new Paint();
            Assets.textPaint.setTextSize(32);
            Assets.textPaint.setColor(Color.BLUE);
            Assets.textPaint.setTypeface(typeface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
