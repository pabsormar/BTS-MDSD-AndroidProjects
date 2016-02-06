package org.bts_netmind.broadcastreceivermanager;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private SystemBroadcastReceiver mySystemReceiver;
    // The following declaration will be used with the LocalBroadcastManager class
    private LocalBroadcastReceiver myLocalReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button trigBroadcast_Btn = (Button) this.findViewById(R.id.btnTrigBroadcast);
            trigBroadcast_Btn.setOnClickListener(this);

        this.myLocalReceiver = new LocalBroadcastReceiver();
        final Button trigLocalBroadcast_Btn = (Button) this.findViewById(R.id.btnTrigLocalBroadcast);
            trigLocalBroadcast_Btn.setOnClickListener(this);

        // These lines enable/disable intent-filters which have been declared in the Manifest
        //ComponentName receiver = new ComponentName(this, CustomBroadcastReceiver.class);
        //PackageManager pm = this.getPackageManager();
        //    pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, 0);
    }

    // A broadcast dynamic register can only be made once, so watch out to unregister it at some point
    @Override
    protected void onResume()
    {
        super.onResume();
        // We cannot use an unnamed SystemBroadcastReceiver(), since we need to have a reference to unregister it at some point
        this.mySystemReceiver = new SystemBroadcastReceiver();
        // The SystemBroadcastReceiver() is now bound/attached to listen a specific System broadcast
        this.registerReceiver(this.mySystemReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Receiver REGISTERED in onResume()");

        // These line makes use of the LocalBroadcastManager class to handle a local event
        LocalBroadcastManager.getInstance(this).registerReceiver(this.myLocalReceiver, new IntentFilter("my-custom-intentfilter"));
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Receiver REGISTERED in onResume()");
    }

    // This is an in-class BroadcastReceiver to be consumed locally
    private class LocalBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "triggered by LocalBroadcastManager");
        }
    }

    // Have a look to the Activity lifecycle to figure out where to unregister for a broadcast
    // hint: onPause() - onResume(); onStop() - onCreate()/onRestart(); onDestroy() - onCreate();
    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(this.mySystemReceiver);
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Receiver UNREGISTERED in onPause()");

        // We now unregister using the LocalBroadcastManager
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mySystemReceiver);
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Receiver UNREGISTERED in onPause()");
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.btnTrigBroadcast)
        {
            //Intent customIntent = new Intent();
            //customIntent.setAction("org.bts_netmind.CUSTOM_INTENT");
            //this.sendBroadcast(customIntent);

            //Intent customIntent = new Intent().setAction("org.bts_netmind.CUSTOM_INTENT");
            //this.sendBroadcast(customIntent);

            this.sendBroadcast(new Intent("org.bts_netmind.CUSTOM_INTENT"));
        }
        else if (whichView.getId() == R.id.btnTrigLocalBroadcast)
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("my-custom-intentfilter"));
    }
}
