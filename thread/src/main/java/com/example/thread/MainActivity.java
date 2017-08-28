package com.example.thread;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private TextView mPercentTextView;

    // Main Thread 로 전달하는 매개체
    private Handler mHanlder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mPercentTextView = (TextView) findViewById(R.id.percent_text);

        mHanlder = new Handler();
    }

    public void startDownload(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 다운로드
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(100);

                        Log.d(TAG, "run: " + i);
                        mProgressBar.setProgress(i + 1);

                        final int percent = i + 1;
                        // UI 갱신
                        mHanlder.post(new Runnable() {
                            @Override
                            public void run() {
                                mPercentTextView.setText(percent + "%");
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
