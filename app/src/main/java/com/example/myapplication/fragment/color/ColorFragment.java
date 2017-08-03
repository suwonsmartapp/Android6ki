package com.example.myapplication.fragment.color;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    private static final String TAG = ColorFragment.class.getSimpleName();

    // 프래그먼트는 반드시 빈 생성자만 있어야 한다
    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment newInstance(int color) {

        Bundle args = new Bundle();
        args.putInt("color", color);

        ColorFragment fragment = new ColorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ColorFragment newInstance() {

        Bundle args = new Bundle();

        int r = new Random().nextInt(256);
        int g = new Random().nextInt(256);
        int b = new Random().nextInt(256);
        int color = Color.argb(255, r, g, b);
        args.putInt("color", color);

        ColorFragment fragment = new ColorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_color, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int color = getArguments().getInt("color");

        view.setBackgroundColor(color);
    }

}
