package com.example.memonodb.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.memonodb.R;
import com.example.memonodb.activity.MainActivity;
import com.example.memonodb.models.Memo;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoDetailFragment extends Fragment {

    public static final String MEMO_KEY = "memo";

    private EditText mTitleEditText;
    private EditText mMemoEditText;

    public static MemoDetailFragment newInstance(Memo memo) {
        Bundle args = new Bundle();
        args.putParcelable(MEMO_KEY, memo);

        MemoDetailFragment fragment = new MemoDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_memo_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitleEditText = view.findViewById(R.id.title_edit);
        mMemoEditText = view.findViewById(R.id.memo_edit);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Memo memo = new Memo(mTitleEditText.getText().toString(),
                        mMemoEditText.getText().toString());

                Intent intent = new Intent();
                intent.putExtra(MainActivity.KEY_DATA, memo);

                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });

        if (getArguments() != null) {
            Memo memo = getArguments().getParcelable(MEMO_KEY);
            mTitleEditText.setText(memo.getTitle());
            mMemoEditText.setText(memo.getMemo());
        }
    }
}
