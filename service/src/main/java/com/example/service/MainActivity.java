package com.example.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartService(View view) {
        Intent service = new Intent(this, MyService.class);
        startService(service);
    }

    public void onIntentService(View view) {
        Intent service = new Intent(this, MyIntentService.class);
        startService(service);
    }
}
