package com.example.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private User mUser = new User("준석", "오");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setUser(mUser);
        mBinding.setActivity(this);
    }

    public void onClick(View v) {
        Toast.makeText(this, "클릭", Toast.LENGTH_SHORT).show();

        mUser.setFirstName("상배");
        mBinding.setUser(mUser);
    }
}
