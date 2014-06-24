package com.luka.dodgetheshark.game;

import android.media.AudioManager;
import android.media.SoundPool;

import com.luka.dodgetheshark.assets.Data;

/**
 * Created by Luka on 24/06/14.
 */
public class SoundController {

    private SoundPool pool;
    public int pop;
    public int lwoo;
    public int click;
    public int hwoo;

    public SoundController() {
        this.pool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        this.pop =  this.pool.load(Data.Assets.pop , 1);
        this.click = this.pool.load(Data.Assets.click , 2);
        this.lwoo = this.pool.load(Data.Assets.lwoo , 3);
        this.hwoo = this.pool.load(Data.Assets.hwoo , 4);
    }

    public void play(final int id) {
        pool.play(id,1.0f, 1.0f, 0, 0, 1);
    }

}
