package com.luka.dodgetheshark;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Assets;
import com.luka.dodgetheshark.assets.Statics;
import com.luka.dodgetheshark.game.PlayerFish;
import com.luka.dodgetheshark.game.Shark;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
    private SoundPool pool;
    private int pop;
    private int spikes;
    private int x;
    private int y;
    private int hx;
    private final String score = "Score";
    private boolean gameStarted;
    private Random random;
    private boolean remove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        this.fish = new PlayerFish(50,50);
        this.spikes = (int) Math.ceil(width/20);
        this.random = new Random();
        this.shark = new Shark(width,50, height);
        this.sharks = new ArrayList<Shark>();
        this.sharks.add(shark);
        this.x = (width/2) - (Assets.ready.getWidth()/2);
        this.y = (height/2) - (Assets.ready.getHeight()/2);
        this.thread = new Thread(this);
        this.remove = false;

        Statics.score = 0;
    }

    @Override
    public Canvas draw(Canvas canvas) {
        for(int i = 0 ; i < spikes ; i++) {
            canvas.drawBitmap(Assets.deathspike, i*20, 0, null);
            canvas.drawBitmap(Assets.ideathspike, i*20, height-40, null);
        }
        canvas.drawBitmap(Assets.fish, fish.x, fish.y, null);
        for(Shark shar : sharks) {
                canvas.drawBitmap(Assets.shark, shar.x, shar.y, null);
        }

        if(!gameStarted) {
            canvas.drawBitmap(Assets.ready, x, y, null);
        }

        return canvas;
    }

    private boolean collideWithSpikes() {
        return fish.y >= height-40 || fish.y <= 40;
    }

    private boolean collision() {
        for(Shark shar : sharks) {

            int fishx = fish.x;
            int fishy = fish.y;
            if ((fishx - shar.x >= -25 && fishx-shar.x <= 25)  && (fishy - shar.y <= 40 && fish.y - shar.y >= -40)) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldSpawn() {
        int score = Statics.score;
        return random.nextInt(14000) < score*5;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.pop = this.pool.load(Assets.pop , 1);
        running = true;
        this.gameStarted = false;
        if(thread.getState().equals(Thread.State.NEW)) {
            thread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.pool.unload(pop);
        running = false;
        if(isFinishing()) {
            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(gameStarted) {
            pool.play(pop,1.0f, 1.0f, 0, 0, 1);
            fish.move(true);
        } else {
            gameStarted = true;
        }
        return false;
    }

    @Override
    public void run() {
        while (running) {
            if(gameStarted) {
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
            try {
                thread.sleep(35);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
