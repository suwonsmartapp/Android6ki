package com.example.myapplication;

import android.content.Intent;
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
        Button button = (Button) findViewById(R.id.coffee_button);
        button.setOnClickListener(listener);

        findViewById(R.id.basketball_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 화면 새로 띄우는 코드
                Intent intent = new Intent(ActivityMoveActivity.this,
                        BasketBallActivity.class);
                startActivity(intent);
            }
        });


        // 데이터 전송
        findViewById(R.id.send_data_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "최지수씨 열심히 하세요";
                Intent intent = new Intent(ActivityMoveActivity.this,
                        TargetActivity.class);
                intent.putExtra("data", message);
                intent.putExtra("age", 10);
                startActivity(intent);
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
