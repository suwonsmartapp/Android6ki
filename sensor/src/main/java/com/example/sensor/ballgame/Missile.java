package com.example.sensor.ballgame;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by junsuk on 2017. 10. 31..
 */

public class Missile {
    private final double N;
    private Drawable mDrawable;
    private int mWidth;
    private int mHeight;
    private Rect mRect;

    private Rect mViewRect;

    private double mDx;
    private double mDy;

    private final double mAcceleration = 1.5;

    private double mDistance = 0.00000000001;

    private final double M;

    public Missile(Drawable drawable, Rect viewRect,
                   double x, double y,
                   double x2, double y2) {
        mDrawable = drawable;
        mWidth = drawable.getIntrinsicWidth() / 2;
        mHeight = drawable.getIntrinsicHeight() / 2;
        mRect = new Rect(0, 0, mWidth, mHeight);

        mViewRect = viewRect;

        mRect.offset(
                (mViewRect.right - mViewRect.left - mWidth) / 2,
                (mViewRect.bottom - mViewRect.top - mHeight) / 2);

        mDx = x;
        mDy = y;


        // 기울기
        M = (y2 - y) / (x2 - x);
        // Y절편
        N = y / (M * x);

        // X좌표가 중앙의 오른쪽일 경우에 왼쪽으로 이동하도록
        if (mViewRect.width() / 2 > x) {
            mDistance *= -1;
        }

    }

    public void move() {
        mDx = mDx + mDistance;
        mDy = M * mDx + N;

        mRect.offset((int) mDx, (int) mDy);
    }

    public void draw(Canvas canvas) {
        mDrawable.setBounds(mRect);
        mDrawable.draw(canvas);
    }
}
