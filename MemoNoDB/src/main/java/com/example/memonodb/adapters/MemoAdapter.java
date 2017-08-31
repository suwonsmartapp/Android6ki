package com.example.memonodb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.memonodb.models.Memo;

import java.util.List;

/**
 * Created by junsuk on 2017. 8. 31..
 */

public class MemoAdapter extends BaseAdapter {
    private List<Memo> mMemoList;

    public MemoAdapter(List<Memo> memoList) {
        mMemoList = memoList;
    }

    @Override
    public int getCount() {
        return mMemoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMemoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(android.R.layout.simple_list_item_2, viewGroup, false);

            holder.title = convertView.findViewById(android.R.id.text1);
            holder.memo = convertView.findViewById(android.R.id.text2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Memo memo = mMemoList.get(position);

        holder.title.setText(memo.getTitle());
        holder.memo.setText(memo.getMemo());

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView memo;
    }
}
