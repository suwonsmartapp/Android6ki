package com.example.cursorproviderglide;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.grid_view);

        // 권한 요청
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);

        MyPhotoCursorAdapter adapter = new MyPhotoCursorAdapter(MainActivity.this, cursor);

        mGridView.setAdapter(adapter);
    }
}
