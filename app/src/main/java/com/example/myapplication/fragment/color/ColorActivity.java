package com.example.myapplication.fragment.color;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.R;

public class ColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
    }

    public void onCreateRedFragmentButtonClicked(View view) {
        ColorFragment fragment = ColorFragment.newInstance(Color.RED);

        // R.id.container 영역에 프래그먼트를 교체하겠다
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onCreateBlueFragmentButtonClicked(View view) {
        ColorFragment fragment = ColorFragment.newInstance(Color.BLUE);

        // R.id.container 영역에 프래그먼트를 교체하겠다
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onCreateGreenFragmentButtonClicked(View view) {
        ColorFragment fragment = ColorFragment.newInstance(Color.GREEN);

        // R.id.container 영역에 프래그먼트를 교체하겠다
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onCreateRandomFragmentButtonClicked(View view) {
        ColorFragment fragment = ColorFragment.newInstance();

        // R.id.container 영역에 프래그먼트를 교체하겠다
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
