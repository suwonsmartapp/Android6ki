package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SignUpMessageActivity extends AppCompatActivity {

    private TextView mMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_message);

        mMessageTextView = (TextView) findViewById(R.id.message_text);

        Intent intent = getIntent();
        if (intent != null) {
            String id = intent.getStringExtra("id");
            String password = intent.getStringExtra("password");
            String email = intent.getStringExtra("email");
            String gender = intent.getStringExtra("gender");

            mMessageTextView.setText(id + ", " + password + ", " + email + ", " + gender);
        }
    }

    public void confirm(View view) {
        setResult(RESULT_OK);
        finish();
    }
}
