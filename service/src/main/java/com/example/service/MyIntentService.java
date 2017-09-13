package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

public class MyIntentService extends IntentService {


    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @WorkerThread
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "MyIntentService: " + i);

                EventBus.getDefault().post(new CallbackEvent(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CallbackEvent {

        public CallbackEvent(int i) {
            this.i = i;
        }

        int i;
    }
}
