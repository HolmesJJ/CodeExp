package com.example.codeexp.ui.widget;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.codeexp.util.DensityUtils;

public class LoadingView extends View {

    private int mStartColorNormal = Color.parseColor("#FFFFFFFF");
    private int mEndColorNormal = Color.parseColor("#00FFFFFF");
    private Paint mPaint = null;
    private RectF mRectF;
    private SweepGradient mSweepGradientTopNormal;
    private SweepGradient mSweepGradientBottomNormal;
    private ObjectAnimator mObjectAnimator = null;

    public LoadingView(Context context) {

        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {

        this(context, attrs, 0);
    }


    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(DensityUtils.dip2px(2));

        PropertyValuesHolder rotate = PropertyValuesHolder.ofFloat("rotation", 0, 359);
        //PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.8f, 0.8f, 0.2f, 0f);
        mObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, rotate);
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setDuration(1000L / 24 * 27);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        drawArc(canvas);
    }

    private void drawArc(Canvas canvas) {

        if (mRectF == null) {
            int distance = DensityUtils.dip2px( 1);
            mRectF = new RectF(distance, distance, getWidth() - distance, getWidth() - distance);
        }
        if (mSweepGradientTopNormal == null) {
            mSweepGradientTopNormal = new SweepGradient(getWidth() / 2, getWidth() / 2, new int[]{mEndColorNormal, mStartColorNormal}, new float[]{0f, 0.5f});
        }
        if (mSweepGradientBottomNormal == null) {
            mSweepGradientBottomNormal = new SweepGradient(getWidth() / 2, getWidth() / 2, new int[]{mEndColorNormal, mStartColorNormal}, new float[]{0.5f, 1f});
        }
        canvas.rotate(90, getWidth() / 2, getHeight() / 2);
        mPaint.setShader(mSweepGradientTopNormal);
        canvas.drawArc(mRectF, 0, 179, false, mPaint);
        mPaint.setShader(mSweepGradientBottomNormal);
        canvas.drawArc(mRectF, 180, 179, false, mPaint);
    }


    public void start() {

        mObjectAnimator.start();
    }


    public void stop() {

        if (mObjectAnimator != null) {
            mObjectAnimator.cancel();
            mObjectAnimator.end();
        }
    }
}
