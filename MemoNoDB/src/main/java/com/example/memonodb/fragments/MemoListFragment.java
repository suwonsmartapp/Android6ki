package com.example.memonodb.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by junsuk on 2017. 8. 30..
 */

public class MemoListFragment extends ListFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Map<String, String>> memoList = new ArrayList<>();
        Map<String, String> data1 = new HashMap<>();
        data1.put("title", "제목 1");
        data1.put("memo", "메모 1");
        memoList.add(data1);
        Map<String, String> data2 = new HashMap<>();
        data2.put("title", "제목 2");
        data2.put("memo", "메모 2");
        memoList.add(data2);
        Map<String, String> data3 = new HashMap<>();
        data3.put("title", "제목 3");
        data3.put("memo", "메모 3");
        memoList.add(data3);

        // 어댑터 완성
        SimpleAdapter adapter = new SimpleAdapter(getContext(),
                memoList,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "memo"},
                new int[]{android.R.id.text1, android.R.id.text2});

        setListAdapter(adapter);
    }
}
