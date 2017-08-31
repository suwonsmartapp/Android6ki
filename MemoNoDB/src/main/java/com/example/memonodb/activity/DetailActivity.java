package com.example.memonodb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.memonodb.R;
import com.example.memonodb.fragments.MemoDetailFragment;
import com.example.memonodb.models.Memo;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Memo memo = null;
        if (intent != null) {
            memo = intent.getParcelableExtra(MainActivity.KEY_DATA);
        }

        if (savedInstanceState == null) {
            if (memo == null) {
                // 추가
                addFragmentTransaction(new MemoDetailFragment());
            } else {
                // 생성된 메모 열기
                addFragmentTransaction(MemoDetailFragment.newInstance(memo));
            }
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
}
