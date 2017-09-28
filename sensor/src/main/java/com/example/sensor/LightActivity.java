package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class LightActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = LightActivity.class.getSimpleName();
    private SensorManager mSensorManager;
    private Sensor mLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 모든 센서 목록
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Log.d(TAG, "onCreate: " + deviceSensors);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            // Success! There's a magnetometer.
            List<Sensor> lightSensors = mSensorManager.getSensorList(Sensor.TYPE_LIGHT);

            Log.d(TAG, "onCreate: " + lightSensors);

            // 조도 센서
            mLight = lightSensors.get(0);


        } else {
            // Failure! No magnetometer.
            Toast.makeText(this, "조도 센서가 없습니다", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "lux : " + sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
