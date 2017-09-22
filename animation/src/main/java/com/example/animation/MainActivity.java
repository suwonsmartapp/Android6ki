package com.example.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mTargetView;

    private boolean animated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTargetView = (FrameLayout) findViewById(R.id.target);
    }

    public void startAnimation(View view) {
        if (animated = !animated) {
            mTargetView.animate()
                    .translationY(mTargetView.getHeight())
                    .setDuration(500)
                    .start();

        } else {
            mTargetView.animate()
                    .translationY(0)
                    .setDuration(500)
                    .start();
        }
    }

    public void key_clicked(View view) {

    }
}
