package com.example.music;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music.util.ImageUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 1000;
    ListView mSongListView;
    TextView mTitleTextView;
    TextView mArtistTextView;
    ImageButton mPrevButton;
    ImageButton mPlayButton;
    ImageButton mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSongListView = (ListView) findViewById(R.id.song_list_view);
        mTitleTextView = (TextView) findViewById(R.id.title_text_view);
        mArtistTextView = (TextView) findViewById(R.id.artist_text_view);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPlayButton = (ImageButton) findViewById(R.id.play_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
            return;
        }

        getSongList();

        mPlayButton.setOnClickListener(this);
    }

    private void getSongList() {
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);

        MyMusicCursorAdapter adapter = new MyMusicCursorAdapter(this, cursor);

        mSongListView.setAdapter(adapter);

        mSongListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor currentCursor = (Cursor) parent.getAdapter().getItem(position);
                String uri = currentCursor.getString(currentCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                Toast.makeText(MainActivity.this, uri, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, MyMusicService.class);
                intent.setAction(MyMusicService.ACTION_PLAY);
                intent.putExtra("uri", Uri.parse(uri));
                startService(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getSongList();
            } else {
                Toast.makeText(this, "권한 거절 ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                onPlayButtonClicked();
                break;
        }
    }

    private void onPlayButtonClicked() {
        Intent intent = new Intent(this, MyMusicService.class);
        intent.setAction(MyMusicService.ACTION_PAUSE);
        startService(intent);
    }

    @Subscribe
    public void onUiChangeEvent(MyMusicService.UiChangeEvent event) {
        if (event.isPlaying) {
            ImageUtil.changeVectorDrawable(this, mPlayButton, R.drawable.ic_pause_black_24dp);
        } else {
            ImageUtil.changeVectorDrawable(this, mPlayButton, R.drawable.ic_play_arrow_black_24dp);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
