package com.example.memonodb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.memonodb.R;
import com.example.memonodb.fragments.MemoListFragment;
import com.example.memonodb.models.Memo;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_MEMO = 1000;
    public static final int REQUEST_CODE_MEMO_UPDATE = 2000;

    public static final String KEY_DATA = "data";
    private MemoListFragment mMemoListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, DetailActivity.class), REQUEST_CODE_ADD_MEMO);
            }
        });

        // 한번만 붙이겠다
        if (savedInstanceState == null) {
            mMemoListFragment = new MemoListFragment();
            addFragmentTransaction(mMemoListFragment);
        }
    }

    private void addFragmentTransaction(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    private void replaceFragmentTransaction(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            Memo memo = data.getParcelableExtra(KEY_DATA);

            switch (requestCode) {
                case REQUEST_CODE_ADD_MEMO:
                    mMemoListFragment.addMemo(memo);
                    break;
                case REQUEST_CODE_MEMO_UPDATE:
                    mMemoListFragment.updateMemo(memo);
                    break;
            }
        }
    }
}
