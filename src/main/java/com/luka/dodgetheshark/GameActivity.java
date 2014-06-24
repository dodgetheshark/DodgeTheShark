package com.luka.dodgetheshark;

import android.content.Intent;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Data;
import com.luka.dodgetheshark.game.PlayerFish;
import com.luka.dodgetheshark.game.Shark;
import com.luka.dodgetheshark.ui.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Luka on 18/06/14.
 */
public class GameActivity extends AbstractActivity implements Runnable {

    private boolean running;
    private PlayerFish fish;
    private Shark shark;
    public List<Shark> sharks;
    public Thread thread;
    private int pop;
    private int spikes;
    private boolean gameStarted;
    private Random random;
    private boolean paused;
    private Button pauseb;
    private int previousScore;
    private StringBuilder builder;
    private String current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.fish = new PlayerFish(50,50);
        this.spikes = (int) Math.ceil(width/20);
        this.random = new Random();
        this.shark = new Shark(width,50, height);
        this.sharks = new ArrayList<Shark>();
        this.sharks.add(shark);
        this.thread = new Thread(this);
        this.paused = false;
        this.pauseb = new Button(width-100,height-100, Data.Assets.pausedButton);
        Data.score = 0;
        this.previousScore = 0;
        this.builder = new StringBuilder();
        this.current = getStringAsText();
    }

    @Override
    public Canvas draw(Canvas canvas) {
        if(previousScore != Data.score) {
            current = getStringAsText();
            previousScore = Data.score;
        }
        canvas.drawText(current,Data.Calculations.widthFifty, 75 , Data.Assets.textPaint);

        for(int i = 0 ; i < spikes ; i++) {
            canvas.drawBitmap(Data.Assets.deathspike, i*20, 0, null);
            canvas.drawBitmap(Data.Assets.ideathspike, i*20, height-40, null);
        }
        canvas.drawBitmap(Data.Assets.fish, fish.x, fish.y, null);
        canvas = pauseb.draw(canvas);
        for(Shark shar : sharks) {
            canvas.drawBitmap(Data.Assets.shark, shar.x, shar.y, null);
        }

        if(!gameStarted) {
            canvas.drawBitmap(Data.Assets.ready, Data.Calculations.readyX, Data.Calculations.readyY, null);
        }

        if(paused) {
            canvas.drawBitmap(Data.Assets.pausedMemo, Data.Calculations.readyX, Data.Calculations.readyY, null);
        }


        return canvas;
    }

    private String getStringAsText() {
        builder.setLength(0);
        builder.append(Data.Constants.score).append(Data.score);
        return builder.toString();
    }

    private boolean collideWithSpikes() {
        return fish.y >= height-40 || fish.y <= 40;
    }

    private boolean collision() {
        for(Shark shar : sharks) {
            int fishx = fish.x+fish.image.getWidth();
            int fishy = fish.y+fish.image.getHeight();
            if ((fishx) - shar.x >= 0 && fishx-shar.x <= 50 && (fishy - shar.y >= 30 && fish.y - shar.y <= 50)) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldSpawn() {
        int score = Data.score;
        return random.nextInt(10000) < score*10;
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        this.gameStarted = false;
        if(thread.getState().equals(Thread.State.NEW)) {
            thread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        if(isFinishing()) {
            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(gameStarted) {
            if(!paused) {
                if(pauseb.clicked((int)motionEvent.getX(),(int)motionEvent.getY())) {
                    paused = true;
                    return false;
                }
                Data.controller.play(Data.controller.pop);
                fish.move(true);
            } else {
                paused = false;
            }
        } else {
            gameStarted = true;
        }

        return false;
    }

    @Override
    public void run() {
        while (running) {
            if(gameStarted) {
                if(!paused) {
                    if(collision() || collideWithSpikes()) {
                        running = false;
                        Intent intent = new Intent(getApplicationContext(), HSActivity.class);
                        startActivity(intent);
                    }
                    fish.move(false);
                    for (Shark shar : sharks) {
                        shar.move();
                    }

                    if(shouldSpawn()) {
                        sharks.add(new Shark(width, random.nextInt(height+10), height));
                    }
                }
            }
            try {
                thread.sleep(35);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
