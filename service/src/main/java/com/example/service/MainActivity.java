package com.example.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent mServiceIntent;
    private MyService mMyService;
    private boolean mBound;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartService(View view) {
        mServiceIntent = new Intent(this, MyService.class);
        startService(mServiceIntent);

        // 종료
//        stopService(mServiceIntent);
    }

    public void onIntentService(View view) {
        Intent service = new Intent(this, MyIntentService.class);
        startService(service);
    }

    public void onBindService(View view) {
        Intent service = new Intent(this, MyService.class);
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMyService = ((MyService.MyBinder) service).getService();
                mBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mBound = false;
            }
        };
        bindService(service, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mBound) {
            unbindService(mServiceConnection);
        }
    }

    public void getValue(View view) {
        if (mBound) {
            Toast.makeText(this, mMyService.getValue() + "", Toast.LENGTH_SHORT).show();
        }
    }
}
