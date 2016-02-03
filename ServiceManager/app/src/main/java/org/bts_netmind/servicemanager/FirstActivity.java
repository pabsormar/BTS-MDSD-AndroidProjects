package org.bts_netmind.servicemanager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends Activity implements View.OnClickListener
{
    private static final String TAG_ACTIVITY_ID = "In-FirstActivity";

    private TextView txtViewInfo;
    private EditText edtTextInput;
    // This piece corresponds to the BoundService part
    BoundService mBoundService;
    boolean isBound = false;
    boolean boundOnceAtLeast = false;
    // Defines callbacks for service binding, passed to bindService() method
    private ServiceConnection mServConnection = new ServiceConnection()
    {
        // The system calls this to deliver the IBinder returned by the service's onBind() method
        @Override
        public void onServiceConnected(ComponentName name, IBinder servBinder)
        {
            // We bind to BoundService using a constructor with an IBinder as an input argument
            mBoundService = new BoundService(servBinder);
            isBound = true;
        }
        // The Android system calls this when the connection to the service is unexpectedly lost such as
        // when the service has crashed or has been killed. This is not called when the client unbinds
        @Override
        public void onServiceDisconnected(ComponentName name) { isBound = false; }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        this.txtViewInfo = (TextView) this.findViewById(R.id.txtViewAction);

        this.edtTextInput = (EditText) this.findViewById(R.id.edtTxtInputInfo);

        final Button btnShow = (Button) this.findViewById(R.id.btnDispInfo);
            btnShow.setOnClickListener(this);

        final Button btnStartServ = (Button) this.findViewById(R.id.btnStartService);
            btnStartServ.setOnClickListener(this);

        final Button btnStartBoundServ = (Button) this.findViewById(R.id.btnStartBoundService);
            btnStartBoundServ.setOnClickListener(this);

        final Button btnStartIntServ = (Button) this.findViewById(R.id.btnStartIntentService);
            btnStartIntServ.setOnClickListener(this);
    }

    @Override
    public void onClick(View vClicked)
    {
        switch (vClicked.getId())
        {
            case R.id.btnDispInfo:
                Log.i(FirstActivity.TAG_ACTIVITY_ID, "Display Button clicked");

                if (!isBound)
                {
                    //stopService(new Intent(this, StartedService.class));
                    this.displayInfo(false);
                }
                else
                    this.displayInfo(true);
                break;

            case R.id.btnStartService:
                Log.i(FirstActivity.TAG_ACTIVITY_ID, "Start Service Button clicked");
                Intent startedServIntent = new Intent(this, StartedService.class);
                startService(startedServIntent);
                break;

            case R.id.btnStartBoundService:
                Log.i(FirstActivity.TAG_ACTIVITY_ID, "Start bound Service Button clicked");
                Intent boundServIntent = new Intent(this, BoundService.class);
                // It is a good practice to start the Service and later bind to it
                this.startService(boundServIntent);
                this.bindService(boundServIntent, mServConnection, Context.BIND_AUTO_CREATE);
                this.boundOnceAtLeast = true;
                break;

            case R.id.btnStartIntentService:
                Log.i(FirstActivity.TAG_ACTIVITY_ID, "Starting IntentService Button clicked");
                Intent intServIntent = new Intent(this, MyIntentService.class);
                this.startService(intServIntent);
                break;
        }
    }

    private void displayInfo(boolean bFlag)
    {
        if (!this.edtTextInput.getText().toString().isEmpty())
            this.txtViewInfo.setText(this.edtTextInput.getText().toString());
        else if(bFlag)
            this.txtViewInfo.setText(Integer.toString(mBoundService.getRandomNumber()));
        else
            this.txtViewInfo.setText(R.string.txt_view_disclaimer);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        if (!isBound && boundOnceAtLeast)
        {
            Log.i("Activity onRestart()", "BoundService bind");
            Intent boundServIntent = new Intent(this, BoundService.class);
            // It is a good practice to start the Service and later bind to it
            this.startService(boundServIntent);
            this.bindService(boundServIntent, mServConnection, Context.BIND_AUTO_CREATE);
            isBound = true;
        }
    }

    // We unbind the bound Service once the Activity is not visible anymore
    // Remember binding is useful to retrieve info from the service, so user interaction is required
    @Override
    protected void onStop()
    {
        super.onStop();

        if (isBound)
        {
            Log.i("Activity onPause()", "BoundService unbind");
            this.unbindService(mServConnection);
            isBound = false;
        }
    }

    // The next 2 methods handle the restart process of the Activity if it is killed by the system
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("currentString", this.txtViewInfo.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        this.txtViewInfo.setText(savedInstanceState.getString("currentString"));
    }
}
