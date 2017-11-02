package com.example.alexey.audiostreamer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by alexey
 */

public class PlayerService extends Service
        implements AudioManager.OnAudioFocusChangeListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener {

    private static final String ACTION_PLAY = "com.example.alexey.audiostreamer.action.PLAY";
    private static final String ACTION_STOP = "com.example.alexey.audiostreamer.action.STOP";
    public static final String PLAYER_READY = "com.example.alexey.audiostreamer.action.PLAYER_READY";

    private static final String EXTRA_STREAM_URL = "STREAM_URL";

    public static final String EXTRA_PLAYER_PREPARED = "PLAYER_PREPARED";

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private String streamUrl;

    public static void startActionPlay(Context context, String streamUrl) {
        Intent intent = new Intent(context, PlayerService.class);
        intent.setAction(ACTION_PLAY);
        intent.putExtra(EXTRA_STREAM_URL, streamUrl);
        context.startService(intent);
    }

    public static void startActionStop(Context context) {
        Intent intent = new Intent(context, PlayerService.class);
        intent.setAction(ACTION_STOP);
        context.startService(intent);
    }

    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PLAY.equals(action)) {
                final String streamUrl = intent.getStringExtra(EXTRA_STREAM_URL);
                handleActionPlay(streamUrl);
            } else if (ACTION_STOP.equals(action)) {
                handleActionStop();
            }
        }
        return START_STICKY;
    }

    private void handleActionPlay(String streamUrl) {
        this.streamUrl = streamUrl;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            handleActionStop();
        }
        if (requestFocus()) {
            if (mediaPlayer == null) {
                initMediaPlayer();
            }
        }
    }

    private void handleActionStop() {
        if (abandonFocus()) {
            releasePlayer();
        }
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // resume playback
                if (mediaPlayer == null) {
                    initMediaPlayer();
                } else if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                mediaPlayer.setVolume(1.0f, 1.0f);
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                // Lost focus for an unbounded amount of time: stop playback and release media player
                releasePlayer();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // Lost focus for a short time, but we have to stop
                // playback. We don't release the media player because playback
                // is likely to resume
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.setVolume(0.1f, 0.1f);
                }
                break;
        }
    }

    private void releasePlayer() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnErrorListener(this);

        try {
            mediaPlayer.setDataSource(streamUrl);

            mediaPlayer.prepareAsync();
        } catch (IOException ignored) {
            sendPreparedResult(true);
        }
    }

    @Override
    public void onPrepared(MediaPlayer player) {
        player.start();

        sendPreparedResult(true);
    }

    @Override
    public boolean onError(MediaPlayer player, int what, int extra) {
        player.reset();

        sendPreparedResult(false);

        return false;
    }

    private void sendPreparedResult(boolean successful) {
        Intent intent = new Intent(PLAYER_READY);
        intent.putExtra(EXTRA_PLAYER_PREPARED, successful);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private boolean requestFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                audioManager.requestAudioFocus(this,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN);
    }

    private boolean abandonFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                audioManager.abandonAudioFocus(this);
    }

}
