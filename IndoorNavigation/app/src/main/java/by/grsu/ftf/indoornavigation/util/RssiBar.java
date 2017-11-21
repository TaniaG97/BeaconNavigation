package by.grsu.ftf.indoornavigation.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import by.grsu.ftf.indoornavigation.R;

/**
 * Created by Admin on 15.11.2017.
 */

public class RssiBar extends View {
    private float value;
    private int colorBar;
    private Paint mPaint = new Paint();
    private RectF mBoundsF;
    final float START_ANGLE = 0.f;
    float mDrawTo;



    public RssiBar (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RssiBar, 0, 0);
        value = typedArray.getFloat(R.styleable.RssiBar_value,0);
        colorBar = typedArray.getColor(R.styleable.RssiBar_color, Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float x = this.getMeasuredWidth();
        float y = this.getMeasuredHeight();

        mPaint.setColor(Color.GRAY);
        canvas.drawRect(0,0,x,y,mPaint);
        mPaint.setColor(colorBar);
        canvas.drawRect(0,0,value*x,y,mPaint);

////        Круговая диаграмма
//        mBoundsF=new RectF(0,0,x,y);
//        mDrawTo = START_ANGLE + ((float)360*value);
//        canvas.rotate(-90f, x/2, y/2);
//        mPaint.setColor(Color.GRAY);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawOval(mBoundsF, mPaint);
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(mBoundsF, START_ANGLE, mDrawTo, true, mPaint);
    }

    public void setValue(float value) {
        this.value = value;
        invalidate();
        requestLayout();
    }
}
