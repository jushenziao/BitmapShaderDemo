package com.jushenziao.admin.bitmapshaderdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class ActionView extends View {
    private Paint mFillPaint;
    private BitmapShader mBitmapShader;

    private float posX, posY;// 触摸点的XY坐标
    private AnimatorSet mAnimatorSet;

    public ActionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.DKGRAY);
        canvas.drawCircle(posX, posY, 100, mFillPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        initAnimation();
    }

    private void initAnimation() {
        ValueAnimator widthAnimator = ObjectAnimator.ofFloat(0, getMeasuredWidth());
        ValueAnimator heightAnimator = ObjectAnimator.ofFloat(0, getMeasuredHeight());

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                posX = (Float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        heightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                posY = (Float) valueAnimator.getAnimatedValue();
            }
        });

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(heightAnimator).with(widthAnimator);
        mAnimatorSet.setDuration(3000);
    }

    private void initPaint() {

        mFillPaint = new Paint();
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mFillPaint.setShader(mBitmapShader);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mAnimatorSet.start();
                break;
        }
        return true;
    }
}
