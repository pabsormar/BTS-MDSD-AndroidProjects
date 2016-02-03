package org.bts_netmind.servicemanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartedService extends Service
{
    private static final String TAG_STARTED_SERVICE = "In-StartedService";
    private static int numStartedServs;

    public StartedService() { }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(StartedService.TAG_STARTED_SERVICE, "StartedService onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        numStartedServs++;
        Log.i(StartedService.TAG_STARTED_SERVICE, "StartedService onStartCommand() num = " + StartedService.numStartedServs);

        long endTime = System.currentTimeMillis() + (2 * 1000);

        while (System.currentTimeMillis() < endTime)
        {
            synchronized (this)
            {
                try
                {
                    Log.i(StartedService.TAG_STARTED_SERVICE, "Long-time process...");
                    this.wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        // The return value is an int representing what to do if the system kills the app/Activity
        return Service.START_NOT_STICKY;   // or return Service.START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(StartedService.TAG_STARTED_SERVICE, "StartedService onDestroy()");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
