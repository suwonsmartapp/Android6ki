package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityMoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        View.OnClickListener listener = new MyClickListner();
//        View.OnClickListener listener2 = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ActivityMoveActivity.this, "잘 된다", Toast.LENGTH_SHORT).show();
//            }
//        };

        Button button = (Button) findViewById(R.id.coffee_button);
        button.setOnClickListener(listener);

        findViewById(R.id.basketball_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class MyClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(ActivityMoveActivity.this, "잘 되나?", Toast.LENGTH_SHORT).show();
        }
    }

}
