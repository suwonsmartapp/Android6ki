package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG = MyService.class.getSimpleName();

    private MyBinder mBinder = new MyBinder();

    private int i = 0;

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

    public MyService() {
    }

    @MainThread
    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        Log.d(TAG, "onStartCommand: " + i);
                        if (mCallback != null) {
                            mCallback.onCallback(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 끝
                stopSelf(startId);
            }
        }).start();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    public int getValue() {
        return i;
    }

    public interface IServiceCallback {
        void onCallback(int value);
    }

    IServiceCallback mCallback;

    public void setCallback(IServiceCallback callback) {
        mCallback = callback;
    }
}
