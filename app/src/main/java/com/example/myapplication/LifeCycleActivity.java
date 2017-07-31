package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class LifeCycleActivity extends AppCompatActivity {

    private static final String TAG = LifeCycleActivity.class.getSimpleName();

    // 액티비티가 실행 될 때
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        // 초기화
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    // 재개, 화면이 보이기 직전
    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: ");
    }


    // 일시 정지, 화면에서 안 보이기 직전
    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause: ");
    }

    // 정지, 화면에서 안 보이게 되면
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    // 액티비티 종료 될 때
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 메모리 해제
        Log.d(TAG, "onDestroy: ");
    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("test");
        builder.setMessage("test");
        builder.show();
    }
}
