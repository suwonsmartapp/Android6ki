package com.example.memosqlite.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.memorealm.R;
import com.example.memosqlite.models.Memo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoDetailFragment extends Fragment {

    @BindView(R.id.title_edit)
    EditText mTitleEdit;
    @BindView(R.id.memo_edit)
    EditText mMemoEdit;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    Unbinder unbinder;

    private Realm mRealm;

    private Memo mMemo;

    public static final int MODE_ADD = 0;
    public static final int MODE_UPDATE = 1;

    private int mMode = MODE_ADD;

    public static MemoDetailFragment newInstance(Memo memo) {
        MemoDetailFragment fragment = new MemoDetailFragment();
        fragment.setMemo(memo);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        mRealm = Realm.getDefaultInstance();

        if (mMode == MODE_UPDATE) {
            mTitleEdit.setText(mMemo.getTitle());
            mMemoEdit.setText(mMemo.getMemo());
        }

        return view;
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        mRealm.beginTransaction();

        Memo memo = null;
        if (mMode == MODE_ADD) {
            memo = mRealm.createObject(Memo.class, Memo.getNewId(mRealm));
        } else {
            memo = mMemo;
        }
        memo.setTitle(mTitleEdit.getText().toString());
        memo.setMemo(mMemoEdit.getText().toString());

        mRealm.insertOrUpdate(memo);
        mRealm.commitTransaction();

        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mRealm.close();
    }

    public void setMemo(Memo memo) {
        this.mMemo = memo;
        mMode = MODE_UPDATE;
    }
}
