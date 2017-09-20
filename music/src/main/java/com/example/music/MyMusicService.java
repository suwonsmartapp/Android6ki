package com.example.music;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

public class MyMusicService extends Service {
    public static final String ACTION_PLAY = "com.example.music.action.play";
    public static final String ACTION_PAUSE = "com.example.music.action.pause";
    public static final String ACTION_PREV = "com.example.music.action.prev";
    public static final String ACTION_NEXT = "com.example.music.action.next";
    private Cursor mCursor;

    private MediaPlayer mMediaPlayer;
    private Uri mUri;

    private final IBinder mBinder = new MyMusicServiceBinder();

    public MyMusicService() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case ACTION_PLAY:
                int position = intent.getIntExtra("position", -1);
                if (position != -1) {
                    mCursor.moveToPosition(position);
                }
                Uri uri = Uri.parse(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));

                if (mMediaPlayer.isPlaying()) {
                    pause();
                }
                if (uri != null) {
                    mUri = uri;
                }

                try {
                    play(mUri);
                    startForeground(1, createNotification());
                } catch (IOException e) {
                    Toast.makeText(this, "에러!!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case ACTION_PAUSE:
                pause();
                stopForeground(false);
                break;
            case ACTION_PREV:
                if (mMediaPlayer.isPlaying()) {
                    pause();
                }
                if (mCursor.moveToPrevious()) {
                    mUri = Uri.parse(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                    try {
                        play(mUri);
                        startForeground(1, createNotification());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ACTION_NEXT:
                if (mMediaPlayer.isPlaying()) {
                    pause();
                }
                if (mCursor.moveToNext()) {
                    mUri = Uri.parse(mCursor.getString(mCursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                    try {
                        play(mUri);
                        startForeground(1, createNotification());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

        return START_NOT_STICKY;
    }

    private Notification createNotification() {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this, mUri);
        byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();

        Bitmap bitmap = null;
        if (picture != null) {
            bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
        } else {
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentTitle("뮤직플레이어");
        builder.setContentText("노래 제목");

        builder.setStyle(new NotificationCompat.MediaStyle());

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        createAction(builder,
                MyMusicService.ACTION_PREV,
                1002,
                R.drawable.ic_skip_previous_black_24dp,
                "prev");

        createAction(builder,
                MyMusicService.ACTION_PLAY,
                1001,
                R.drawable.ic_play_arrow_black_24dp,
                "play");

        createAction(builder,
                MyMusicService.ACTION_NEXT,
                1003,
                R.drawable.ic_skip_next_black_24dp,
                "next");

        return builder.build();
    }

    private void createAction(NotificationCompat.Builder builder,
                              String action,
                              int requestCode,
                              int icon,
                              String title) {
        Intent intent = new Intent(this, MyMusicService.class);
        intent.setAction(action);

        PendingIntent pendingIntent = PendingIntent.getService(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Action NotiAction = new Action(icon,
                title, pendingIntent);
        builder.addAction(NotiAction);
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
