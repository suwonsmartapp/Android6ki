package com.example.myapplication.adapterview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;

public class DetailAddressActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mPhoneEditText;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_address);

        mNameEditText = (EditText) findViewById(R.id.name_edit);
        mPhoneEditText = (EditText) findViewById(R.id.phone_edit);
        mImageView = (ImageView) findViewById(R.id.image_view);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            int picture = intent.getIntExtra("picture", R.mipmap.ic_launcher);

            mNameEditText.setText(name);
            mPhoneEditText.setText(phone);
            mImageView.setImageResource(picture);
        }
    }
}
