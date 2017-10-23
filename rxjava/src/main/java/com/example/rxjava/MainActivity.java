package com.example.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> observable = Observable.fromArray("test", "테스트", "test1", "test2");

        observable.observeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(s -> s.contains("테스트"))
                .subscribe(this::showLog);
    }

    private void showLog(String message) {
        Log.d(TAG, "showLog: " + message);
    }
}
