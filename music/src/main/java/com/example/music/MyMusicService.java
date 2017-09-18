package com.example.music;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class MyMusicService extends Service {
    public static final String ACTION_PLAY = "com.example.music.action.play";
    public static final String ACTION_PAUSE = "com.example.music.action.pause";
    public static final String ACTION_PREV = "com.example.music.action.prev";
    public static final String ACTION_NEXT = "com.example.music.action.next";
    public static final String ACTION_FOREGROUND_SERVICE = "com.example.music.action.foreground_service";

    private MediaPlayer mMediaPlayer;
    private Uri mUri;

    private final IBinder mBinder = new MyMusicServiceBinder();

    public MyMusicService() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case ACTION_PLAY:
                if (mMediaPlayer.isPlaying()) {
                    pause();
                }

                mUri = intent.getParcelableExtra("uri");
                try {
                    play(mUri);
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
            case ACTION_FOREGROUND_SERVICE:
                startForeground(1, createNotification());
                break;
        }

        return START_NOT_STICKY;
    }

    private Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("뮤직플레이어");
        builder.setContentText("노래 제목");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher));

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        return builder.build();
    }

    public void play(Uri uri) throws IOException {
        mMediaPlayer.setDataSource(this, uri);
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();

                UiChangeEvent event = new UiChangeEvent();
                event.isPlaying = mMediaPlayer.isPlaying();
                event.uri = mUri;
                EventBus.getDefault().post(event);
            }
        });
    }

    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();

            UiChangeEvent event = new UiChangeEvent();
            event.isPlaying = mMediaPlayer.isPlaying();
            event.uri = mUri;
            EventBus.getDefault().post(event);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
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
        Uri uri;
    }

    public class MyMusicServiceBinder extends Binder {
        public MyMusicService getService() {
            return MyMusicService.this;
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public void updateUi() {
        UiChangeEvent event = new UiChangeEvent();
        event.isPlaying = mMediaPlayer.isPlaying();
        event.uri = mUri;
        EventBus.getDefault().post(event);
    }

}
