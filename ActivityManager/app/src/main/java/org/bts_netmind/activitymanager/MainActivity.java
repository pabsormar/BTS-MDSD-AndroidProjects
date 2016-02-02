package org.bts_netmind.activitymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener
{
    public static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    public static final int ACTIVITY_REQUEST_CODE = 100;

    private TextView dispInfo_TextView;
    private String retInfoString;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout mainLayout = (RelativeLayout) this.findViewById(R.id.layoutMain);
            mainLayout.setOnClickListener(this);

        this.dispInfo_TextView = (TextView) this.findViewById(R.id.txtViewDispInfo);

        final Button dispInfo_Btn = (Button) this.findViewById(R.id.btnDispInfo);
            dispInfo_Btn.setOnClickListener(this);
        /*this.dispInfo_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*long a = 0;
                while (a < 1000000000)
                    a++;
             }
         });*/

        final Button startAct_Btn = (Button) this.findViewById(R.id.btnStartAct);
            startAct_Btn.setOnClickListener(this);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        //Toast.makeText(this, "Main Act onStart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //Toast.makeText(this, "Main Act onResume()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Toast.makeText(this, "Main Act onPause()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        //Toast.makeText(this, "Main Act onStop()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //Toast.makeText(this, "Main Act onDestroy()", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        //Toast.makeText(this, "Main Act onRestart()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.btnStartAct)
        {
            Intent actIntent = new Intent(this, SecondActivity.class);
            this.startActivityForResult(actIntent, MainActivity.ACTIVITY_REQUEST_CODE);
        }
        else if (v.getId() == R.id.btnDispInfo)
        {
            if (retInfoString != null && !retInfoString.isEmpty())
                dispInfo_TextView.setText(retInfoString);
        }
        else if (v.getId() == R.id.layoutMain)
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Layout onClick()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == MainActivity.ACTIVITY_REQUEST_CODE)
        {
            if (data != null)
                this.retInfoString = data.getStringExtra("returnString");
        }
    }

    // The next 2 following methods deal with saving non-persistent information when the Activity is
    // unexpectedly recreated (e.g. screen rotation, memory requirements from the OS, etc.)
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("savedReturnString", this.retInfoString);
        outState.putString("currentTextViewString", this.dispInfo_TextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        if (this.dispInfo_TextView != null)
        {
            this.dispInfo_TextView.setText(savedInstanceState.getString("currentTextViewString"));
            this.retInfoString = savedInstanceState.getString("savedReturnString");
        }
    }
}
