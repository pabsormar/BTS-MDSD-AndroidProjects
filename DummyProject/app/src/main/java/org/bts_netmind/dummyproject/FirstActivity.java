package org.bts_netmind.dummyproject;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;

public class FirstActivity extends Activity implements View.OnClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private SystemBroadcastReceiver mySystemReceiver;
    private Button trigBroadcast_Btn;
    private Button trigLocalBroadcast_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        this.trigBroadcast_Btn = (Button) this.findViewById(R.id.btnTrigBroadcast);
            this.trigBroadcast_Btn.setOnClickListener(this);

        this.trigLocalBroadcast_Btn = (Button) this.findViewById(R.id.btnTrigLocalBroadcast);
            this.trigLocalBroadcast_Btn.setOnClickListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        this.mySystemReceiver = new SystemBroadcastReceiver();
        this.registerReceiver(this.mySystemReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        Log.i(FirstActivity.TAG_MAIN_ACTIVITY, "Receiver REGISTERED in onResume()");
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        this.unregisterReceiver(this.mySystemReceiver);
        Log.i(FirstActivity.TAG_MAIN_ACTIVITY, "Receiver UNREGISTERED in onPause()");
    }

    @Override
    public void onClick(View whoClicked)
    {
        if (whoClicked.getId() == R.id.btnTrigBroadcast)
        {
            Log.i(FirstActivity.TAG_MAIN_ACTIVITY, "Button1 clicked");

            Intent customIntent = new Intent();
            customIntent.setAction("susannaWantsThis");
            this.sendBroadcast(customIntent);

            //this.sendBroadcast(new Intent("susannaWantsThis"));
        }
        else if (whoClicked.getId() == R.id.btnTrigLocalBroadcast)
        {
            Log.i(FirstActivity.TAG_MAIN_ACTIVITY, "Button2 clicked");

        }

    }
}
