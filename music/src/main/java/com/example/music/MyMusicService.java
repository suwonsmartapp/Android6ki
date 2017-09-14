package com.example.music;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class MyMusicService extends Service {
    public static final String ACTION_PLAY = "com.example.music.action.play";
    public static final String ACTION_PAUSE = "com.example.music.action.pause";
    public static final String ACTION_PREV = "com.example.music.action.prev";
    public static final String ACTION_NEXT = "com.example.music.action.next";

    private MediaPlayer mMediaPlayer;

    public MyMusicService() {
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case ACTION_PLAY:
                Uri uri = intent.getParcelableExtra("uri");
                try {
                    play(uri);
                } catch (IOException e) {
                    Toast.makeText(this, "에러!!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case ACTION_PAUSE:
                pause();
                break;
            case ACTION_PREV:
                break;
            case ACTION_NEXT:
                break;
        }

        return START_NOT_STICKY;
    }

    public void play(Uri uri) throws IOException {
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setDataSource(this, uri);
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();

                EventBus.getDefault().post(new UiChangeEvent(mMediaPlayer.isPlaying()));
            }
        });
    }

    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();

            EventBus.getDefault().post(new UiChangeEvent(mMediaPlayer.isPlaying()));
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    public static class UiChangeEvent {
        boolean isPlaying;

        public UiChangeEvent(boolean isPlaying) {
            this.isPlaying = isPlaying;
        }
    }
}
