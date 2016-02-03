package org.bts_netmind.servicemanager;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class MyIntentService extends IntentService
{
    public static final String TAG_INTENT_SERVICE = "In-IntentService";
    public static int numIntentServs;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        numIntentServs++;
        Log.i(MyIntentService.TAG_INTENT_SERVICE, "IntentService onHandleIntent() num = " + MyIntentService.numIntentServs);

        long endTime = System.currentTimeMillis() + (5 * 1000);

        while (System.currentTimeMillis() < endTime)
        {
            synchronized (this)
            {
                try
                {
                    Log.i(MyIntentService.TAG_INTENT_SERVICE, "Long-time process...");
                    this.wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}
