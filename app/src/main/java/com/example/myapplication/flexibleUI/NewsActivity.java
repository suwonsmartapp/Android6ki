package com.example.myapplication.flexibleUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;

public class NewsActivity extends AppCompatActivity implements FragmentA.OnHeadlineClickListener {

    private FragmentB mFragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        if (findViewById(R.id.container) != null) {
            // 세로
            // 화면 회전 처리, 중복해서 프래그먼트가 생성되지 않도록
            if (savedInstanceState == null) {
                FragmentA fragment = new FragmentA();

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }

        } else {
            // 가로
            mFragmentB = (FragmentB) getSupportFragmentManager()
                    .findFragmentById(R.id.frag_b);
        }
    }

    @Override
    public void onHeadlineClicked(int position) {
        if (mFragmentB == null) {
            FragmentB fragment = FragmentB.newInstance(position);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            mFragmentB.updateArticleView(position);
        }
    }

}
