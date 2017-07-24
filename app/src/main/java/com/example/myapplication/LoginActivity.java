package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_LOGIN = 1000;
    public static final int REQUEST_CODE_SIGN_UP = 2000;
    private EditText mIdEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.sign_up_text).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                login();
                break;
            case R.id.sign_up_text:
                signUp();
                break;
        }
    }

    private void signUp() {
        Intent intent = new Intent(LoginActivity.this, Target2Activity.class);
        startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
    }

    private void login() {
        Intent intent = new Intent(LoginActivity.this, Target2Activity.class);
        intent.putExtra("id", mIdEditText.getText().toString());
        intent.putExtra("password", mPasswordEditText.getText().toString());
        startActivityForResult(intent, REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOGIN &&
                    data != null) {
                String text = data.getStringExtra("text");
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

            } else if (requestCode == REQUEST_CODE_SIGN_UP) {
                Toast.makeText(this, "가입 잘 되었음", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
