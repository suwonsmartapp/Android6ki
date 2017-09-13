package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

public class MyIntentService extends IntentService {


    public static final String TAG = MyIntentService.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService() {
        super(TAG);
    }

    @WorkerThread
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "MyIntentService: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
