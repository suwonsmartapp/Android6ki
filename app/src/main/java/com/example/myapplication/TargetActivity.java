package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tartget);

        // 넘어온 데이터 받기
        Intent intent = getIntent();
        if (intent != null) {
            String message = intent.getStringExtra("data");
            int age = intent.getIntExtra("age", 0);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void onFinishButtonClicked(View view) {
        // 나이 돌려주자
        Intent intent = new Intent();
        intent.putExtra("age", 28);
        setResult(RESULT_OK, intent);

//        // 성공
//        setResult(RESULT_OK);
//        // 실패
//        setResult(RESULT_CANCELED);

        // 액티비티 종료
        finish();
    }
}
