package com.luka.dodgetheshark.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

import com.luka.dodgetheshark.AbstractActivity;
import com.luka.dodgetheshark.assets.Data;

/**
 * Created by Luka on 18/06/14.
 */
public class RenderView extends View {

    private AbstractActivity abstractActivity;
    private int background_repeat;
    private int width;
    private int height;
    private final Rect rect = new Rect(0,0,20,400);
    private Rect src;

    public RenderView(Context context, AbstractActivity abstractActivity) {
        super(context);
        this.abstractActivity = abstractActivity;
        DisplayMetrics metrics = new DisplayMetrics();
       abstractActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.width = metrics.heightPixels;
        this.height = metrics.widthPixels;

        this.background_repeat = width/20;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Rect srcc = new Rect(0,0,height,width);
        canvas.drawBitmap(Data.Assets.background, rect, srcc, null);
        canvas = abstractActivity.draw(canvas);
        super.onDraw (canvas);
        invalidate();
    }
}
