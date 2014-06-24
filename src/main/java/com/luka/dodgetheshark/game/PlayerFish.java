package com.luka.dodgetheshark.game;

import com.luka.dodgetheshark.assets.Data;

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
        image = Data.Assets.fish;
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
            } else {
                y += velocity;
                velocity++;
            }
        }
    }


}
