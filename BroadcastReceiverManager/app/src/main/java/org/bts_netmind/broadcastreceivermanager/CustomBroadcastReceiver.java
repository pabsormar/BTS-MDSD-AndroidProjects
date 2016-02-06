package org.bts_netmind.broadcastreceivermanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomBroadcastReceiver extends BroadcastReceiver
{
    private static final String TAG_CUSTOM_BROAD_RECEIVER = "In-CustomBroadReceiver";

    public CustomBroadcastReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent.getAction().equalsIgnoreCase("org.bts_netmind.CUSTOM_INTENT"))
            Log.i(CustomBroadcastReceiver.TAG_CUSTOM_BROAD_RECEIVER, "triggered by CUSTOM_INTENT");
    }
}
