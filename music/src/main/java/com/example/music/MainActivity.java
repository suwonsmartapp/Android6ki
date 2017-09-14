package com.example.music;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.song_list_view)
    ListView mSongListView;
    @BindView(R.id.title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.artist_text_view)
    TextView mArtistTextView;
    @BindView(R.id.prev_button)
    ImageButton mPrevButton;
    @BindView(R.id.play_button)
    ImageButton mPlayButton;
    @BindView(R.id.next_button)
    ImageButton mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
}
