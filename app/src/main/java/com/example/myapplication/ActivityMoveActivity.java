package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityMoveActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_AGE = 1000;

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
                String message = "최지수씨 몇 살 이에요?";
                Intent intent = new Intent(ActivityMoveActivity.this,
                        TargetActivity.class);
                intent.putExtra("data", message);
                intent.putExtra("age", 10);
                startActivityForResult(intent, REQUEST_CODE_AGE);
            }
        });

    }

    // startActivityForResult 결과 받는 곳
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AGE &&
                resultCode == RESULT_OK &&
                data != null) {
            int age = data.getIntExtra("age", 0);
            Toast.makeText(this, "" + age, Toast.LENGTH_SHORT).show();
        }
    }

    class MyClickListner implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(ActivityMoveActivity.this, "잘 되나?", Toast.LENGTH_SHORT).show();
        }
    }

}
