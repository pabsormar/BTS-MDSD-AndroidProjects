package org.bts_netmind.servicemanager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class BoundService extends Service
{
    private static final String TAG_BOUND_SERVICE = "In-BoundService";
    private IBinder aBinder = new Binder();   // This has to be initialized!
    private final Random numGenerator = new Random();

    public BoundService() { }
    public BoundService(IBinder whichBinder)
    {
        Log.i(BoundService.TAG_BOUND_SERVICE, "BoundService constructor called");
        this.aBinder = whichBinder;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(BoundService.TAG_BOUND_SERVICE, "BoundService onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.i(BoundService.TAG_BOUND_SERVICE, "BoundService onBind()");
        // TODO: Return the communication channel to the service.
        return this.aBinder;
    }

    // It MUST return 'true' if you want 'onRebind()' to be called
    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.i(BoundService.TAG_BOUND_SERVICE, "BoundService onUnbind()");
        return super.onUnbind(intent);
        // if 'return true;' --- onRebind() is called
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(BoundService.TAG_BOUND_SERVICE, "BoundService onDestroy()");
    }

    public int getRandomNumber() { return this.numGenerator.nextInt(100); }
}
