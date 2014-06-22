package com.luka.dodgetheshark;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Assets;
import com.luka.dodgetheshark.ui.Button;

/**
 * Created by Luka on 19/06/14.
 */
public class HomePageActivity extends AbstractActivity {

    private Button play;
    private Button scores;
    private int x;
    private int y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        x = width/2 - Assets.header.getWidth()/2;
        y = height/3 - Assets.header.getHeight()/3;
        play = new Button(x, (height/3)*2, Assets.play);
        scores = new Button((x+Assets.header.getWidth()-Assets.scores.getWidth()),(height/3)*2, Assets.scores);
    }

    @Override
    public Canvas draw(Canvas canvas) {
        canvas.drawBitmap(Assets.header,x,y ,null);
        canvas = play.draw(canvas);
        canvas = scores.draw(canvas);
        return canvas;
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();

        if(scores.clicked(x,y)) {
            Intent intent = new Intent(getApplicationContext(), HSActivity.class);
            startActivity(intent);
        } else if(play.clicked(x,y)) {
            Intent intent = new Intent(getApplicationContext(), GameActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
