package com.example.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by junsuk on 2017. 10. 30..
 */

public class TouchView extends View {
    private static final String TAG = TouchView.class.getSimpleName();

    private Path mPath = new Path();
    private Paint mPaint = new Paint();

    // 코드로 생성할 때
    public TouchView(Context context) {
        this(context, null);
    }

    // XML로 생성할 때
    public TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // ??
    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    // 뷰의 크기 결정
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 뷰의 위치 결정
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    // 뷰를 그린다
    @Override
    protected void onDraw(Canvas canvas) {
        // 최대한 일을 안 시켜야 된다
        // 객체 생성 하지 말아야 된다
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: " + event.toString());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(event);
                break;
            case MotionEvent.ACTION_UP:
                actionUp(event);
                break;
        }

        // onDraw 호출
//        invalidate();

        invalidate((int) (event.getX() - 20),
                ((int) event.getY() - 20),
                ((int) event.getX() + 20),
                ((int) event.getY() + 20));

        return true;
    }

    private void actionUp(MotionEvent event) {
        mPath.lineTo(event.getX(), event.getY());
//        mPath.close();
    }

    private void actionMove(MotionEvent event) {
        mPath.lineTo(event.getX(), event.getY());
    }

    private void actionDown(MotionEvent event) {
        mPath.reset();
        mPath.moveTo(event.getX(), event.getY());
    }

    public void save() {
        // 빈 비트맵
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_4444);

        // 켄버스를 통해서 bitmap에 그림 그릴 수 있음
        Canvas canvas = new Canvas(bitmap);

        // 그리기
        canvas.drawPath(mPath, mPaint);

        saveBitmaptoJpeg(bitmap, "picture", "pic");
    }

    public static void saveBitmaptoJpeg(Bitmap bitmap, String folder, String name) {
        String ex_storage = Environment.getExternalStorageDirectory().getAbsolutePath();
        // Get Absolute Path in External Sdcard
        String foler_name = "/" + folder + "/";
        String file_name = name + ".jpg";
        String string_path = ex_storage + foler_name;

        File file_path;
        try {
            file_path = new File(string_path);
            if (!file_path.isDirectory()) {
                file_path.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(string_path + file_name);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();

        } catch (FileNotFoundException exception) {
            Log.e("FileNotFoundException", exception.getMessage());
        } catch (IOException exception) {
            Log.e("IOException", exception.getMessage());
        }
    }
}
