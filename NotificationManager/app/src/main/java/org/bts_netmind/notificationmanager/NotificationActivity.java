package org.bts_netmind.notificationmanager;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

public class NotificationActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(MainActivity.NOTIFICATION_ID);
    }
}
