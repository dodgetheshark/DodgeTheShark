package com.luka.dodgetheshark;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.luka.dodgetheshark.assets.Data;
import com.luka.dodgetheshark.ui.Button;

/**
 * Created by Luka on 19/06/14.
 */
public class HomePageActivity extends AbstractActivity {

    private Button play;
    private Button scores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        play = new Button(Data.Calculations.headerX, (height/3)*2, Data.Assets.play);
        scores = new Button((Data.Calculations.headerX+Data.Assets.header.getWidth()-Data.Assets.scores.getWidth()),(height/3)*2, Data.Assets.scores);
    }

    @Override
    public Canvas draw(Canvas canvas) {
        canvas.drawBitmap(Data.Assets.header,Data.Calculations.headerX,Data.Calculations.headerY ,null);
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
