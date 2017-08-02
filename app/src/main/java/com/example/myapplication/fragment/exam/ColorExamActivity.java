package com.example.myapplication.fragment.exam;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.fragment.color.ColorFragment;

public class ColorExamActivity extends AppCompatActivity {

    private ColorFragment mFragment1;
    private ColorFragment mFragment2;
    private ColorFragment mFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_exam);

        mFragment1 = ColorFragment.newInstance(Color.RED);
        mFragment2 = ColorFragment.newInstance(Color.BLUE);
        mFragment3 = ColorFragment.newInstance(Color.GREEN);
    }

    public void onButtonClicked(View view) {

        ColorFragment fragment = null;
        int container = R.id.container_1;
        switch (view.getId()) {
            case R.id.button_1:
                container = R.id.container_1;
                fragment = mFragment1;
                break;
            case R.id.button_2:
                container = R.id.container_2;
                fragment = mFragment2;
                break;
            case R.id.button_3:
                container = R.id.container_3;
                fragment = mFragment3;
                break;
        }
        // 안 붙었으면 넣고 붙었으면 빼자
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!fragment.isAdded()) {
            transaction.replace(container, fragment);
        } else {
            transaction.remove(fragment);
        }
        transaction.commit();
    }
}
