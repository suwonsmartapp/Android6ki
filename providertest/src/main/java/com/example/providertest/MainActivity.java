package com.example.providertest;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.databaseexam.provider/user"),
                null,
                null,
                null,
                null);

        if (cursor != null) {
            StringBuilder stringBuilder = new StringBuilder();
            while (cursor.moveToNext()) {
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                stringBuilder.append(email).append(", ").append(password).append("\n");
            }
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
            cursor.close();
        }
    }
}
