package com.lmu.ath.shoulderwatch.ui.util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.lmu.ath.shoulderwatch.ui.activities.MainActivity;

import java.util.Date;

/**
 * Created by Martin on 15.07.2016.
 */
public class DrawView extends View {
    private float x;
    private float y;
    private final int r=90;
    private int pos = 45;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Date date = new Date();
    int[] coordinates = {0,0,0,0}; //x1,y1,x2,y2
    Context context;
    Point center = new Point();



    private void setToCenterCoordinates() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels/2;
        int width = displayMetrics.widthPixels/2;

        //Log.d("DRAW VIEW", "center coords: " + width + " , " + height);
        center.x = width;
        center.y = height;

        x = width;
        y= height;

        coordinates[0] = width;
        coordinates[1] = height;
        coordinates[2] = width;
        coordinates[3] = 70;


    }

    public DrawView(Context context) {
        super(context);
        this.context = context;
        setupPaint();
       setToCenterCoordinates();


    }

    private void setupPaint() {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3f);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.context = context;
        setupPaint();
       setToCenterCoordinates();
    }

    public DrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        setupPaint();
        setToCenterCoordinates();

    }


/*
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(coordinates[0], coordinates[1], coordinates[2], coordinates[3], paint);

    }
    */

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawCircle(x, y, r, mPaint);
        paint.setColor(0xFFA2BC13);
        float left = (float)(x+(r+10)*Math.cos(Math.toRadians((pos / 60.0f * 360.0f)-90f)));
        float top = (float)(y+(r+15)*Math.sin(Math.toRadians((pos / 60.0f * 360.0f)-90f)));
        canvas.drawOval(left-10,top-10,left+10,top+10, paint);

        canvas.drawLine(x, y, (float)(x+(r+10)*Math.cos(Math.toRadians((pos / 60.0f * 360.0f)-90f))), (float)(y+(r+15)*Math.sin(Math.toRadians((pos / 60.0f * 360.0f)-90f))), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("ATH", "DOWN " + x);
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
                int p = 180 + (int) (Math.atan2(y - center.y, x - center.x) * 180 / Math.PI);
                if (p > 0 && p < 180) {
                    pos = 45 + (int) Math.ceil(p / 6);
                    invalidate();
                    //Log.d("ATH", "Angle: " + p + "Seconds: " + pos);
                    //Log.d("ATH", "Pos: " + pos);
                }
            }
        return true;
    }


    /**
     * Set coordinates for the line reaching from the center icon to the 5 positions around
     * @param position indikator
     */
    public void updateLine(int position){
       switch (position){
           case 0:
               pos = 45;
               break;
           case 1:
                pos = 50;
               break;
           case 2:
               pos = 60;
               break;
           case 3:
               pos = 10;
               break;
           case 4:
                pos = 15;
               break;
       }
    }

}