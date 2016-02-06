package org.bts_netmind.broadcastreceivermanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SystemBroadcastReceiver extends BroadcastReceiver
{
    private static final String TAG_SYSTEM_BROAD_RECEIVER = "In-SystemBroadReceiver";

    public SystemBroadcastReceiver() { }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED))
            Log.i(SystemBroadcastReceiver.TAG_SYSTEM_BROAD_RECEIVER, "Power connected to the device");
        else if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BATTERY_LOW))
            Log.i(SystemBroadcastReceiver.TAG_SYSTEM_BROAD_RECEIVER, "Battery low");
    }
}