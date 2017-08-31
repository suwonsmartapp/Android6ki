package com.example.memonodb.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.memonodb.activity.DetailActivity;
import com.example.memonodb.activity.MainActivity;
import com.example.memonodb.adapters.MemoAdapter;
import com.example.memonodb.models.Memo;

import java.util.ArrayList;
import java.util.List;

import static com.example.memonodb.activity.MainActivity.REQUEST_CODE_MEMO_UPDATE;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoListFragment extends ListFragment {

    private MemoAdapter mAdapter;
    private List<Memo> mMemoList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMemoList = new ArrayList<>();
        mMemoList.add(new Memo("제목 1", "메모 1"));
        mMemoList.add(new Memo("제목 2", "메모 2"));
        mMemoList.add(new Memo("제목 3", "메모 3"));

        mAdapter = new MemoAdapter(mMemoList);

        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Memo memo = mMemoList.get(position);
        memo.setId(id);

        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(MainActivity.KEY_DATA, memo);
        getActivity().startActivityForResult(intent, REQUEST_CODE_MEMO_UPDATE);
    }

    public void addMemo(Memo memo) {
        mMemoList.add(memo);
        mAdapter.notifyDataSetChanged();
    }

    public void updateMemo(Memo memo) {
        mMemoList.set((int) memo.getId(), memo);
        mAdapter.notifyDataSetChanged();
    }
}
