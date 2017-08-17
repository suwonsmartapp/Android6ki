package com.example.myapplication.flexibleUI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentB extends Fragment {
    public static final String POSITION = "position";
    private int mCurrentPosition = 0;

    public FragmentB() {
        // Required empty public constructor
    }

    public static FragmentB newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);

        FragmentB fragment = new FragmentB();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 복원
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(POSITION);
        }

        if (getArguments() != null) {
            mCurrentPosition = getArguments().getInt(POSITION);
        }
        updateArticleView(mCurrentPosition);
    }

    public void updateArticleView(int position) {
        mCurrentPosition = position;

        TextView textView = (TextView) getView().findViewById(R.id.article_text);
        textView.setText(Articles.Articles[position]);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // 저장
        outState.putInt(POSITION, mCurrentPosition);
    }

}
