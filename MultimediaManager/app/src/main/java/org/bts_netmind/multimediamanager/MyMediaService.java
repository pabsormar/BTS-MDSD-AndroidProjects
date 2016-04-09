package org.bts_netmind.multimediamanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;

public class MyMediaService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener
{
    private static final String TAG_MY_MEDIA_SERVICE = "In-MyMediaService";

    MediaPlayer mPlayerInService;

    @Override
    public IBinder onBind(Intent intent)
    {
        // Not a bound Service, thus it does not return any 'IBinder' reference
        return  null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        this.mPlayerInService = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);
        this.mPlayerInService.setOnCompletionListener(this);
        this.mPlayerInService.setWakeMode(this, PowerManager.PARTIAL_WAKE_LOCK);
        this.mPlayerInService.start();

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer mp)
    {
        this.mPlayerInService.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        // Once the track has finished, the 'MediaPlayer' is released (has to do with the WAKE_LOCK)
        mp.release();
        mp = null;
    }

    // Performing cleanup
    @Override
    public void onDestroy()
    {
        if (this.mPlayerInService != null)
            this.mPlayerInService.release();
    }
}
