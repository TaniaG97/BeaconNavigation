package by.grsu.ftf.indoornavigation.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import by.grsu.ftf.indoornavigation.R;

/**
 * Created by Admin on 15.11.2017.
 */

public class RssiBar2 extends View {
    private float value;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Bitmap blePowerBitmap1;
    Bitmap blePowerBitmap2;
    Bitmap blePowerBitmap3;
    Bitmap blePowerBitmap4;
    Bitmap blePowerBitmap5;
    Rect rectImg;
    Rect rect;



    public RssiBar2 (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RssiBar, 0, 0);
        value = typedArray.getFloat(R.styleable.RssiBar_value,0);

        blePowerBitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.android04);
        blePowerBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.android03);
        blePowerBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.android02);
        blePowerBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.android01);
        blePowerBitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.android00);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float x = this.getMeasuredWidth();
        float y = this.getMeasuredHeight();
        rectImg = new Rect(0, 0, blePowerBitmap1.getWidth(), blePowerBitmap1.getHeight());
        rect = new Rect(0, 0, (int) x, (int) y);

        if(value<=0.2){canvas.drawBitmap(blePowerBitmap1, rectImg, rect, mPaint);}
        if(value>0.2 && value<=0.4){canvas.drawBitmap(blePowerBitmap2, rectImg, rect, mPaint);}
        if(value>0.4 && value<=0.6){canvas.drawBitmap(blePowerBitmap3, rectImg, rect, mPaint);}
        if(value>0.6 && value<=0.8){canvas.drawBitmap(blePowerBitmap4, rectImg, rect, mPaint);}
        if(value>0.8 ){canvas.drawBitmap(blePowerBitmap5, rectImg, rect, mPaint);}

        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(20);
        canvas.drawText(String.valueOf(Math.round(value*100))+"%", x/2, y/2, mPaint);
    }

    public void setValue(float value) {
        this.value = value;
        invalidate();
        requestLayout();
    }
}
