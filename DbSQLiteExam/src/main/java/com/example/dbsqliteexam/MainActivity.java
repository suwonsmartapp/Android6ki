package com.example.dbsqliteexam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.email_edit)
    EditText mEmailEdit;
    @BindView(R.id.password_edit)
    EditText mPasswordEdit;
    @BindView(R.id.new_password_edit)
    EditText mNewPasswordEdit;
    @BindView(R.id.result_text)
    TextView mResultText;

    private UserDbHelper mDbHelper;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDbHelper = UserDbHelper.getInstance(this);

        showResult();
    }

    public void SignUp(View view) {
        try {
            mThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long newId = mDbHelper.insert(mEmailEdit.getText().toString(),
                            mPasswordEdit.getText().toString());

                    if (newId == -1) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "에러", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "가입 됨", Toast.LENGTH_SHORT).show();
                                showResult();
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {

        }

        mThread.start();
    }

    public void SignIn(View view) {
        boolean isSignIn = mDbHelper.signIn(mEmailEdit.getText().toString(),
                mPasswordEdit.getText().toString());

        if (isSignIn) {
            Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "실패", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePassword(View view) {
        if (mDbHelper.updatePassword(mEmailEdit.getText().toString(),
                mNewPasswordEdit.getText().toString())) {
            showResult();
        }
    }

    public void deleteAccount(View view) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        int count = db.delete(UserContract.UserEntry.TABLE_NAME,
                UserContract.UserEntry.COLUMN_NAME_EMAIL + "='"
                        + mEmailEdit.getText().toString() + "' AND "
                        + UserContract.UserEntry.COLUMN_NAME_PASSWORD + "='"
                        + mPasswordEdit.getText().toString() + "'",
                null);

        if (count > 0) {
            Toast.makeText(this, "삭제", Toast.LENGTH_SHORT).show();
            showResult();
        }
    }

    private void showResult() {
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.databaseexam.provider/memo"),
                null,
                null,
                null,
                null);

        if (cursor != null) {
            StringBuilder stringBuilder = new StringBuilder();
            while (cursor.moveToNext()) {
                String email = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_EMAIL));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASSWORD));

                stringBuilder.append(email).append(", ").append(password).append("\n");
            }
            mResultText.setText(stringBuilder.toString());
            cursor.close();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mThread != null && mThread.isAlive()) {
            mThread.interrupt();
        }
    }
}
