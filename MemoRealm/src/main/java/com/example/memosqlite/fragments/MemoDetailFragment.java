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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        mRealm = Realm.getDefaultInstance();
        return view;
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        addMemo(mTitleEdit.getText().toString(),
                mMemoEdit.getText().toString());

        getActivity().finish();
    }

    public void addMemo(String title, String memoText) {
        mRealm.beginTransaction();
        Memo memo = mRealm.createObject(Memo.class, Memo.getNewId(mRealm));
        memo.setTitle(title);
        memo.setMemo(memoText);
        mRealm.commitTransaction();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        mRealm.close();
    }

}
