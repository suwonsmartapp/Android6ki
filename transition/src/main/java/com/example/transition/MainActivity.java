package com.example.transition;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 전체 startActivity 시에 적용되는 전환 효과
//            getWindow().setEnterTransition(new Explode());
//            getWindow().setExitTransition(new Explode());

            // 공유 요소의 전환 효과
            getWindow().setSharedElementEnterTransition(new ChangeBounds());
            getWindow().setSharedElementReturnTransition(new ChangeBounds());
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    public void transition(View view) {
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void activityTransition(View view) {
        View imageView = findViewById(R.id.image_view);


        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "image");

        Intent intent = new Intent(this, TargetActivity.class);

        ActivityCompat.startActivity(this, intent, optionsCompat.toBundle());
    }
}
