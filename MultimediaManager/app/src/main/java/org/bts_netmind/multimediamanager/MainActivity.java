package org.bts_netmind.multimediamanager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button play_Btn = (Button) this.findViewById(R.id.btnPlayTrack);
            play_Btn.setOnClickListener(this);
        final Button stop_Btn = (Button) this.findViewById(R.id.btnStopTrack);
            stop_Btn.setOnClickListener(this);
        final Button sound_Btn = (Button) this.findViewById(R.id.btnPlaySound);
            sound_Btn.setOnClickListener(this);
        final Button playInService_Btn = (Button) this.findViewById(R.id.btnPlaySoundInService);
            playInService_Btn.setOnClickListener(this);
        final Button stopInService_Btn = (Button) this.findViewById(R.id.btnPlaySoundInService);
            stopInService_Btn.setOnClickListener(this);

        this.mPlayer = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);
            this.mPlayer = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.btnPlayTrack)
        {
            if (!this.mPlayer.isPlaying())
            {
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Playing track");
                this.mPlayer.start();
            }
        }
        else if (whichView.getId() == R.id.btnStopTrack)
        {
            if (this.mPlayer.isPlaying())
            {
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Track stopped");
                this.mPlayer.pause();
            }
        }
        else if (whichView.getId() == R.id.btnPlaySound)
        {
            if (this.mPlayer.isPlaying())
                this.mPlayer.pause();

            try
            {
                Uri myUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); // initialize Uri here
                MediaPlayer mPlayer2 = new MediaPlayer();
                    mPlayer2.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                    mPlayer2.setDataSource(this, myUri);
                    /*
                    mPlayer2.prepare();
                    mPlayer2.start();
                    */
                    mPlayer2.setOnPreparedListener(this);
                    mPlayer2.prepareAsync();
            } catch (IOException e) { e.printStackTrace(); }
        }
        else if (whichView.getId() == R.id.btnPlaySoundInService)
        {
            Intent serviceIntent = new Intent(this, MyMediaService.class);
            this.startService(serviceIntent);
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if (this.isFinishing())
        {
            this.mPlayer.stop();
            // Releasing the 'MediaPlayer'
            this.mPlayer.release();
            this.mPlayer = null;
        }

    }

    @Override
    public void onPrepared(MediaPlayer mp)
    {
        mp.start();
    }
}
