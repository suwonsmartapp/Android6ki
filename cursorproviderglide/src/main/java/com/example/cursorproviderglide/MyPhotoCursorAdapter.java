package com.example.cursorproviderglide;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by junsuk on 2017. 8. 25..
 */

public class MyPhotoCursorAdapter extends CursorAdapter {
    public MyPhotoCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false);
        viewHolder.imageView = view.findViewById(R.id.image_view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        // 그림이 데이터 베이스에서 실제로 몇번째 열에 있는지
        int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        String data = cursor.getString(index);

//        viewHolder.imageView.setImageURI(Uri.parse(data));
        Glide.with(context).load(data).into(viewHolder.imageView);
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
