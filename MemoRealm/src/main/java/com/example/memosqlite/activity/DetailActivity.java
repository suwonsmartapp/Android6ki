package com.example.memosqlite.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.memorealm.R;
import com.example.memosqlite.fragments.MemoDetailFragment;
import com.example.memosqlite.models.Memo;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Memo memo = null;
        if (getIntent() != null) {
            int id = getIntent().getIntExtra("id", -1);

            Realm realm = Realm.getDefaultInstance();

            memo = realm.where(Memo.class).equalTo("id", id).findFirst();

            realm.close();
        }

        if (savedInstanceState == null) {
            if (memo == null) {
                // 추가
                addFragmentTransaction(new MemoDetailFragment());
            } else {
                // 수정
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
