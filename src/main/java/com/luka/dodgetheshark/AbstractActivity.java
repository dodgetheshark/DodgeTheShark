package com.luka.dodgetheshark;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.luka.dodgetheshark.view.RenderView;

/**
 * Created by Luka on 18/06/14.
 */
public abstract class AbstractActivity extends Activity implements View.OnTouchListener{

    public RenderView view;
    protected int width;
    protected int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.width = metrics.widthPixels;
        this.height = metrics.heightPixels;
        view = new RenderView(this, this);
        view.setOnTouchListener(this);
        setContentView(view);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, HomePageActivity.class);
            startActivity(intent);
        }
        return false;
    }

    public abstract Canvas draw(Canvas canvas);
}
