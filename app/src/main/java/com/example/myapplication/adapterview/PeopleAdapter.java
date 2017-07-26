package com.example.myapplication.adapterview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by junsuk on 2017. 7. 26..
 */

public class PeopleAdapter extends BaseAdapter {

    private final List<People> mData;

    public PeopleAdapter(List<People> data) {
        mData = data;
    }

    // 데이터 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position번째에 어떤 데이터가 있는지 알려줘야 함
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // 데이터베이스의 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position번째의 레이아웃 완성 해서 알려줘야 함
    // convertView - position번째의 레이아웃의 레퍼런스
    // parent - 이 어댑터가 붙을 부모의 레퍼런스 (ListView나 GridView)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}
