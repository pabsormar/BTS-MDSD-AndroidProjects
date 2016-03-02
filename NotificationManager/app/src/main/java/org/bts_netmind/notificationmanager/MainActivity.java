package org.bts_netmind.notificationmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    public static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    public static final int NOTIFICATION_ID = 100;   // Sets an ID for the notification, so it can be updated (or at least not duplicated)
    public static final int CUSTOM_NOTIFICATION_ID = 200;

    // These fields are declared so that they can be used within the whole 'MainActivity' class
    private NotificationCompat.Builder mBuilder;
    private NotificationCompat.Builder mCustomBuilder;
    private NotificationManager mNotifManager;
    // Just in case we want to employ a counter defined as a class attribute
    //private static int numMessages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button addNotification_Btn = (Button) this.findViewById(R.id.buttonToNotify);
            addNotification_Btn.setOnClickListener(this);
        final Button addCustomNotification_Btn = (Button) this.findViewById(R.id.buttonToCustomNotify);
            addCustomNotification_Btn.setOnClickListener(this);

        //--------------
        // Using the 'AlarmManager' service to start an 'Activity' in 3 seconds
        // 'Activity' intent which is wrapped by a 'PendingIntent' object
        Intent activityIntent = new Intent(this, SecondActivity.class);
        PendingIntent mActPendIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_ONE_SHOT);
        // 'AlarmManager' service call and configuration to 3 seconds from right now
        AlarmManager mAlarmManager = (AlarmManager) this.getSystemService(Service.ALARM_SERVICE);
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000, mActPendIntent);

        Toast.makeText(this, "Alarm set in 3 seconds time", Toast.LENGTH_SHORT).show();


        //--------------
        // Standard Notification
        // Asks for the NOTIFICATION_SERVICE and cast it to the 'NotificationManager' object
        this.mNotifManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);
        // Builds a notification using the 'NotificationCompat.Builder' subclass
        this.mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("This is a simple text for my notification")
                .setTicker("A Notification")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(this.getResources().getString(R.string.notification_long_text)));
                //.setAutoCancel(true);
        // Creates an explicit intent for an Activity in the app
        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        // This 'PendingIntent' wraps the previous 'Intent', so that it can be used later
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        this.mBuilder.setContentIntent(mPendingIntent);


        //--------------
        // Custom layout Notification
        Intent customNotificationIntent = new Intent(this, NotificationActivity.class);
        PendingIntent mCustomPendingIntent = PendingIntent.getActivity(this, 2, customNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Defines general features of the custom notification
        this.mCustomBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.messenger_icon)
                .setAutoCancel(true)
                .setTicker("Custom Notification")
                .setContentIntent(mCustomPendingIntent);

        // Define a 'RemoteViews' object to be consumed by a 'NotificationCompat.Builder' in order to set up a custom layout
        // The comprised graphical elements can be defined either in the XML layout file or here in code. Latter option here.
        RemoteViews mRemoteViews = new RemoteViews(this.getPackageName(), R.layout.layout_custom_notification);
            mRemoteViews.setImageViewResource(R.id.imageViewNotification, R.drawable.messenger_icon);
            mRemoteViews.setTextViewText(R.id.txtViewTitle, this.getResources().getString(R.string.custom_notify_text_1));
            mRemoteViews.setTextViewText(R.id.txtViewBody, this.getResources().getString(R.string.custom_notify_text_2));
            mRemoteViews.setTextViewText(R.id.txtViewFootnote, this.getResources().getString(R.string.custom_notify_text_3));

        // Check which Android version is being run. Pre-JellyBean do not support custom layout Notifications
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            this.mCustomBuilder.setContent(mRemoteViews);
        else
        {
            this.mCustomBuilder.setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("My notification")
                    .setContentText("This is a simple text for my notification");
        }
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.buttonToNotify)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Button notification clicked");
            // Check what happens if the number is incremented and used as Notification ID
                //this.mBuilder.setNumber(MainActivity.numMessages++);
                //this.mNotifManager.notify(MainActivity.numMessages, this.mBuilder.build());
            this.mNotifManager.notify(MainActivity.NOTIFICATION_ID, this.mBuilder.build());
        }
        else if (whichView.getId() == R.id.buttonToCustomNotify)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Button Custom notification clicked");
            this.mNotifManager.notify(MainActivity.CUSTOM_NOTIFICATION_ID, this.mCustomBuilder.build());
        }
    }
}
