package com.example.memonodb.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.memonodb.R;
import com.example.memonodb.fragments.MemoDetailFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            addFragmentTransaction(new MemoDetailFragment());
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
