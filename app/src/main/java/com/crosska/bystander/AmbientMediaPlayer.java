package com.crosska.bystander;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AmbientMediaPlayer extends Service {

    private MediaPlayer mPlayer;

    public AmbientMediaPlayer() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();*/
        mPlayer = MediaPlayer.create(this, R.raw.soundtrack);
        mPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*Toast.makeText(this, "Служба запущена",
                Toast.LENGTH_SHORT).show();*/
        mPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*Toast.makeText(this, "Служба остановлена",
                Toast.LENGTH_SHORT).show();*/
        mPlayer.stop();
    }

}