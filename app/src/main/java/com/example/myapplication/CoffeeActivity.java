package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CoffeeActivity extends AppCompatActivity {

    private TextView mQuantityTextView;
    private int mQuantity = 1;

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 레이아웃 설정
        setContentView(R.layout.activity_coffee);

        // XML에 있는 View의 레퍼런스를 가져오는 방법
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);

//        mQuantityTextView.setText(String.valueOf(mQuantity));
        mQuantityTextView.setText("" + mQuantity);
    }

    public void minusButtonClicked(View view) {
        mQuantity--;
        if (mQuantity < 0) {
            mQuantity = 0;
        }
        mQuantityTextView.setText("" + mQuantity);
    }

    public void plusButtonClicked(View view) {
        mQuantity++;
        mQuantityTextView.setText("" + mQuantity);
    }
}
