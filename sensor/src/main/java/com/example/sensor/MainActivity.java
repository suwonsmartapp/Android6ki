package com.example.sensor;

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

    public void startLightSensorActivity(View view) {
        startActivity(new Intent(this, LightActivity.class));
    }

    public void startCompassActivity(View view) {
        startActivity(new Intent(this, CompassActivity.class));
    }
}
