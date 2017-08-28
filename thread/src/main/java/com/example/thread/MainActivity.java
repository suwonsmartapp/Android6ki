package com.example.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private TextView mPercentTextView;

    // Main Thread 로 전달하는 매개체
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mPercentTextView.setText((msg.arg1 + 1) + "%");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mPercentTextView = (TextView) findViewById(R.id.percent_text);
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
                        runOnUiThread(new Runnable() {
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

    public void 오초_뒤에_토스트_띄우기(View view) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "5초 되었음", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
    }
}
