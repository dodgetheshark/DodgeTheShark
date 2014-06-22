package com.luka.dodgetheshark.game;

import android.support.v7.appcompat.R;

import com.luka.dodgetheshark.GameActivity;
import com.luka.dodgetheshark.assets.Assets;

/**
 * Created by Luka on 18/06/14.
 */
public class PlayerFish extends Fish {

    private final int decrease = 10;
    private final int increase = 19 ;
    private int velocity;
    private boolean accept;

    public PlayerFish(int x, int y) {
        super(x, y);
        image = Assets.fish;
        this.velocity = 0;
        this.accept = true;
    }

    public void move(boolean touch) {
        if(touch) {
           accept = false;
           velocity = increase;
        } else {
            if(accept == false) {
                y -= velocity;
                velocity--;
                if(velocity == 0) {
                    accept = true;
                }
            } else if(accept && velocity == 0) {
                velocity = decrease;
            } else  {
                y += velocity;
                velocity++;
            }
        }
    }


}
