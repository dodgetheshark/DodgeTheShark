package com.luka.dodgetheshark.game;

import com.luka.dodgetheshark.assets.Data;

import java.util.Random;

/**
 * Created by Luka on 19/06/14.
 */
public class Shark extends Fish {

    private int velocity;
    private int orginialX;
    private int height;
    private Random random;
    public boolean invalid;

    public Shark(int x, int y, int height) {
        super(x, y);
        this.orginialX = x;
        this.height = height;
        random = new Random();
        velocity = random.nextInt(20)+20;
        this.invalid = false;
    }

    public void move() {
        if(x > - 100) {
            x -= velocity;
        } else {
            restart();
        }
    }

    private void restart() {
        Data.score++;
        x = orginialX;
        y =  random.nextInt(height);
        if(y <= 15) {
            y+=10;
        } else if(y >= height-20) {
            y-=10;
        }
    }
}
