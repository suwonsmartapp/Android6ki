package com.example.myapplication.fragment.basketball;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by junsuk on 2017. 8. 1..
 */

public class BasketScoreFragment extends Fragment implements View.OnClickListener {

    public interface OnWarningListener {
        void onWarning(String teamName);
    }

    private TextView mScoreTextView;
    private TextView mTeamNameTextView;
    private int mScore;

    private OnWarningListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // 액티비티와 연결 됨
        mListener = (OnWarningListener) context;
    }

    // 뷰를 만드는 곳
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basket_score, container, false);
    }

    // 뷰가 다 만들어 진 다음 호출되는 곳
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mScoreTextView = (TextView) view.findViewById(R.id.score_text);
        mTeamNameTextView = (TextView) view.findViewById(R.id.team_name_text);
        view.findViewById(R.id.button_1).setOnClickListener(this);
        view.findViewById(R.id.button_2).setOnClickListener(this);
        view.findViewById(R.id.button_3).setOnClickListener(this);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt("score");
            mScoreTextView.setText("" + mScore);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("score", mScore);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                mScore++;
                break;
            case R.id.button_2:
                mScore += 2;
                break;
            case R.id.button_3:
                mScore += 3;
                break;
        }
        if (mScore > 20) {
            mListener.onWarning(mTeamNameTextView.getText().toString());
        }
        mScoreTextView.setText("" + mScore);
    }

    public void reset() {
        mScore = 0;
        mScoreTextView.setText("" + mScore);
    }

    public void setTeamName(String name) {
        mTeamNameTextView.setText(name);
    }

    public void warning() {
        getView().setBackgroundColor(Color.RED);
    }
}
