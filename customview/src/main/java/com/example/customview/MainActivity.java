package com.example.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCustomView mMyCustomView;

    private TouchView mTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyCustomView = findViewById(R.id.custom_view);

        mTouchView = findViewById(R.id.touch_view);

//        mMyCustomView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        mMyCustomView.showToast();


        mTouchView.save();
    }

}
