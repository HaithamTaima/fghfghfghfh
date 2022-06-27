package com.example.fainl_android.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.fainl_android.R;

public class ProService extends Service {
    IBinder iBinder = new LocalBinder();
    MediaPlayer mediaPlayer;
    int[] songs = {R.raw.both, R.raw.fast, R.raw.minimal, R.raw.rock, R.raw.simple, R.raw.trailer};

    public ProService() {
    }


    int position = 0;

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("MusicPlayerService", "onBind");
        mediaPlayer = MediaPlayer.create(this, songs[position]);
        mediaPlayer.stop();
        ;
        return iBinder;
    }
    //-------------------------------------------------------

    public class LocalBinder extends Binder {
        public ProService getService() {
            return ProService.this;
        }
    }

    //-------------------------------------------------------
    public void nextSong() {
        if (position < (songs.length - 1)) {
            position++;
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songs[position]);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "هذه الاغنية الاخيرا ", Toast.LENGTH_SHORT).show();
        }
    }

    public void perSong() {
        if (position != 0) {
            position--;
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, songs[position]);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "هذه الاغنية الاولى", Toast.LENGTH_SHORT).show();
        }
    }

    public int getPosition() {
        return position;
    }

    public void pauseSonge() {
        mediaPlayer.pause();
    }

    public void startSonge() {
        mediaPlayer.start();
    }

    //بتجيب لوين واصلة الاغنية
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    //بتجيب طول الاغنية
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void seekTo(int msec) {
        mediaPlayer.seekTo(msec);
    }


}