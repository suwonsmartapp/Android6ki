package com.example.memonodb.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import com.example.memonodb.adapters.MemoAdapter;
import com.example.memonodb.models.Memo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoListFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Memo> memoList = new ArrayList<>();
        memoList.add(new Memo("제목 1", "메모 1"));
        memoList.add(new Memo("제목 2", "메모 2"));
        memoList.add(new Memo("제목 3", "메모 3"));

        MemoAdapter adapter = new MemoAdapter(memoList);

        setListAdapter(adapter);
    }
}
