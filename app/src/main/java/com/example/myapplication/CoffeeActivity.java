package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CoffeeActivity extends AppCompatActivity {

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 레이아웃 설정
        setContentView(R.layout.activity_coffee);

        // XML에 있는 View의 레퍼런스를 가져오는 방법
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);

        quantityTextView.setText("10");
    }

    public void minusButtonClicked(View view) {
        Toast.makeText(this, "잘 눌림", Toast.LENGTH_SHORT).show();
    }

    public void plusButtonClicked(View view) {
    }
}
