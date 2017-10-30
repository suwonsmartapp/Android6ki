package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by junsuk on 2017. 10. 30..
 */

public class MyCustomView extends LinearLayout {

    private static final String TAG = MyCustomView.class.getSimpleName();
    private View mButton;

    // 코드로 생성할 때
    public MyCustomView(Context context) {
        this(context, null);
        Log.d(TAG, "MyCustomView: 1");
    }

    // XML에서 호출
    public MyCustomView(Context context, AttributeSet attrs) {
        this(context, null, 0);
        Log.d(TAG, "MyCustomView: 2");
    }

    // ??
    public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "MyCustomView: 3");
        initLayout();
    }

    private void initLayout() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.my_custom_view, null, true);

        mButton = findViewById(R.id.button);

        addView(view);
    }

    public void setOnClickListener(OnClickListener listener) {
        mButton.setOnClickListener(listener);
    }

    public void showToast() {
        Toast.makeText(getContext(), "토스트", Toast.LENGTH_SHORT).show();
    }

}
