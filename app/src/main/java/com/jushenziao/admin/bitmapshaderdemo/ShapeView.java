package com.jushenziao.admin.bitmapshaderdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class ShapeView extends View {
    private Paint mFillPaint;
    private BitmapShader mBitmapShader;
    private Path mPath;


    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPath.moveTo(400, 400);
        for (int i = 1; i <= 7; i++) {
            //生成15个点,随机生成它们的坐标,并将它们连成一条Path
            mPath.lineTo(i * 20, (float) Math.random() * 60);
        }

        mPath.close();

        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
       // canvas.drawCircle(100, 100, 100, mFillPaint);
        mFillPaint.setTextSize(60);
        canvas.drawText("Android 7.0 Nougat!", 200, 200, mFillPaint);

        canvas.drawPath(mPath, mFillPaint);

    }

    private void initPaint() {

        mFillPaint = new Paint();
        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mFillPaint.setShader(mBitmapShader);
    }
}
