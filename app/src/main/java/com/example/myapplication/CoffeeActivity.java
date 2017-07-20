package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CoffeeActivity extends AppCompatActivity {

    public static final int MIN_QUANTITY = 1;
    public static final int DEFAULT_QUANTITY = 1;
    public static final int COFFEE_PRICE = 3000;

    private TextView mQuantityTextView;
    private TextView mPriceTextView;
    private CheckBox mWippedCreamCheckBox;
    private EditText mNameEditText;

    private int mQuantity = DEFAULT_QUANTITY;

    private DecimalFormat mFormat = new DecimalFormat("#,##0");

    // 메인
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 레이아웃 설정
        setContentView(R.layout.activity_coffee);

        // XML에 있는 View의 레퍼런스를 가져오는 방법
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);
        mPriceTextView = (TextView) findViewById(R.id.price_text);
        mWippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check);
        mNameEditText = (EditText) findViewById(R.id.name_edit);

//        mQuantityTextView.setText(String.valueOf(mQuantity));
        display();
    }

    private void display() {
        mQuantityTextView.setText("" + mQuantity);

        String message = "주문자 : " + mNameEditText.getText().toString();
        message += "\n====================";
        message += "\n휘핑 크림 추가 여부 : " + mWippedCreamCheckBox.isChecked();
        message += "\n갯수 : " + mQuantity;
        message += "\n가격 : " + mFormat.format(mQuantity * COFFEE_PRICE) + "원";

        mPriceTextView.setText(message);
    }

    public void minusButtonClicked(View view) {
        mQuantity--;
        if (mQuantity < MIN_QUANTITY) {
            mQuantity = MIN_QUANTITY;
        }
        display();
    }

    public void plusButtonClicked(View view) {
        mQuantity++;
        display();
    }

    public void onCheckBoxClicked(View view) {
        display();
    }
}
