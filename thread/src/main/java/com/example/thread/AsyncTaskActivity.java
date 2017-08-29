package com.example.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = AsyncTaskActivity.class.getSimpleName();
    private ProgressBar mProgressBar;
    private TextView mPercentTextView;

    // Main Thread 로 전달하는 매개체
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mPercentTextView.setText((msg.arg1 + 1) + "%");
        }
    };
    private DownloadTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mPercentTextView = (TextView) findViewById(R.id.percent_text);
    }

    public void startDownload(View view) {
        if (mTask != null && mTask.isCancelled() == false) {
            mTask.cancel(true);
        }
        mTask = new DownloadTask();
        mTask.execute();

//        task.cancel(true);
    }

    public void 오초_뒤에_토스트_띄우기(View view) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(AsyncTaskActivity.this, "5초 되었음", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
    }

    private class DownloadTask extends AsyncTask<Void, Integer, Void> {

        // doInBackground 전에 호출
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @WorkerThread
        @Override
        protected Void doInBackground(Void... voids) {
            // 다운로드
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);

                    Log.d(TAG, "run: " + i);
                    // UI 갱신
                    publishProgress(i + 1); // onProgressUpdate 호출

                    if (isCancelled()) {
                        Toast.makeText(AsyncTaskActivity.this, "캔슬 됨", Toast.LENGTH_SHORT).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
            mPercentTextView.setText(values[0] + "%");
        }

        // doInBackground 가 끝나고 호출
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
