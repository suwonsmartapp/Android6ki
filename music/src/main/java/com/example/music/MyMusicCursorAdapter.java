package com.example.music;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyMusicCursorAdapter extends CursorAdapter {
    public static final String TAG = MyMusicCursorAdapter.class.getSimpleName();

    public MyMusicCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);

        viewHolder.mImageView = (ImageView) view.findViewById(R.id.image_view);
        viewHolder.mTitleTextView = (TextView) view.findViewById(R.id.title_text_view);
        viewHolder.mArtistTextView = (TextView) view.findViewById(R.id.artist_text_view);

        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        // 그림이 데이터 베이스에서 실제로 몇번째 열에 있는지
        String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

        viewHolder.mTitleTextView.setText(title);
        viewHolder.mArtistTextView.setText(artist);

        final Uri uri = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));

        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(context, uri);
            byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();

            if (picture != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
                viewHolder.mImageView.setImageBitmap(bitmap);
            } else {
                viewHolder.mImageView.setImageResource(R.mipmap.ic_launcher);
            }
        } catch (Exception e) {
            Log.d(TAG, "bindView: " + cursor.getPosition());
        }
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView mTitleTextView;
        TextView mArtistTextView;
    }
}