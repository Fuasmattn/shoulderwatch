package com.lmu.ath.shoulderwatch.ui.util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.ui.activities.MainActivity;

import java.util.Date;

/**
 * Created by Martin on 15.07.2016.
 */
public class DrawView extends View {
    private float x;
    private float y;
    long startTime;
    private final int r=90;
    private int pos = 45;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Date date = new Date();
    Context context;
    Point center = new Point();
    Bitmap icon = loadBitmap(R.mipmap.ic_ptop);
    Bitmap iconSelf = loadBitmap(R.mipmap.ic_top);
    private Matrix matrix;
    private Matrix matrixSelf;



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

        matrix = new Matrix();
        matrixSelf = new Matrix();
        matrixSelf.postTranslate(iconSelf.getWidth()/2, iconSelf.getHeight()/2);
        matrixSelf.postScale(0.5f, 0.5f);

        matrix.postTranslate(10 + x + icon.getWidth()/2,y + icon.getHeight()/2); // Center pivot bmp
        matrix.postScale(0.5f, 0.5f);

        Bitmap tmp = loadBitmap(R.mipmap.ic_top);
        iconSelf = Bitmap.createBitmap(tmp,0,0, tmp.getWidth(), tmp.getHeight(),matrixSelf, true);
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


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawCircle(x, y, r, mPaint);
        paint.setColor(Color.WHITE);
        float left = (float)(x+(r+10)*Math.cos(Math.toRadians((pos / 60.0f * 360.0f)-90f)));
        float top = (float)(y+(r+15)*Math.sin(Math.toRadians((pos / 60.0f * 360.0f)-90f)));

       //icon.setBounds((int)left-20,(int)top-30,(int)left+30,(int)top+40);
        //iconSelf.setBounds((int)x-30,(int)y-30,(int)x+30,(int)y+30);

        canvas.drawLine(x, y, (float)(x+(r+10)*Math.cos(Math.toRadians((pos / 60.0f * 360.0f)-90f))), (float)(y+(r+15)*Math.sin(Math.toRadians((pos / 60.0f * 360.0f)-90f))), paint);

        //icon.draw(canvas);
        //iconSelf.draw(canvas);

        canvas.drawBitmap(icon,matrix,paint);
        canvas.drawBitmap(iconSelf,left - iconSelf.getWidth()/2 ,top - iconSelf.getHeight()/2 ,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //Log.d("ATH", "DOWN " + x);
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            int p = 180 + (int) (Math.atan2(y - center.y, x - center.x) * 180 / Math.PI);

            //Log.d("ATH", "P: " + p + " betweenExclusive(p,0,30) = " + betweenExclusive(p, 0, 30) + " pos = " + pos);

            if      (betweenExclusive(p, 0, 22))    pos = 45;
            else if (betweenExclusive(p, 22, 65))   pos = 52;
            else if (betweenExclusive(p, 65, 110))  pos = 0;
            else if (betweenExclusive(p, 110, 155)) pos = 8;
            else if (betweenExclusive(p, 155, 180)) pos = 15;

            invalidate();
        }
        return true;
    }

        public boolean betweenExclusive(int x, int min, int max)
        {
            return x>min && x<max;
        }


    public void turnIcon() {
        Log.d("ATH", "turnIcon");
        iconSelf= rotate(iconSelf);

        invalidate();
    }

    private Bitmap rotate(Bitmap bitm) {
        Matrix transform = new Matrix();

        transform.reset();
        transform.postTranslate(bitm.getWidth()/2, bitm.getHeight()/2);
        transform.postRotate(180, bitm.getWidth()/2, bitm.getHeight()/2);
        return Bitmap.createBitmap(bitm,0,0,bitm.getWidth(), bitm.getHeight(), transform, true);
    }

    private Bitmap loadBitmap(int resId) {
        Bitmap bmpOriginal = BitmapFactory.decodeResource(getResources(), resId);
        return bmpOriginal;
    }
}