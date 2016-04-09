package org.bts_netmind.multimediamanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class MusicIntentReceiver extends BroadcastReceiver
{
    public MusicIntentReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
        {
            // signal your service to stop playback
            // (via an Intent, for instance)
        }
    }
}
