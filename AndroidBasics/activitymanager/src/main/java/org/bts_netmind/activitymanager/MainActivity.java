package org.bts_netmind.activitymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener
{
    public static final int ACTIVITY_REQUEST_CODE = 100;

    private RelativeLayout mainLayout;
    private TextView dispInfo_TextView;
    private Button dispInfo_Btn;
    private Button startAct_Btn;
    private String retInfoString;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "Main Act onCreate()", Toast.LENGTH_SHORT).show();

        this.mainLayout = (RelativeLayout) this.findViewById(R.id.layoutMain);
        this.mainLayout.setOnClickListener(this);

        this.dispInfo_TextView = (TextView) this.findViewById(R.id.txtViewDispInfo);

        this.dispInfo_Btn = (Button) this.findViewById(R.id.btnDispInfo);
        this.dispInfo_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Toast.makeText(v.getContext(), "Display info button clicked", Toast.LENGTH_SHORT).show();
                if (retInfoString != null && !retInfoString.isEmpty())
                    dispInfo_TextView.setText(retInfoString);
            }
        });

        this.startAct_Btn = (Button) this.findViewById(R.id.btnStartAct);
        this.startAct_Btn.setOnClickListener(this);
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

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.btnStartAct)
        {
            //Toast.makeText(v.getContext(), "Start Activity Button clicked", Toast.LENGTH_SHORT).show();
            Intent actIntent = new Intent(this, SecondActivity.class);
            this.startActivityForResult(actIntent, MainActivity.ACTIVITY_REQUEST_CODE);
        }
        else if (v.getId() == R.id.layoutMain)
            Toast.makeText(v.getContext(), "Main Act layout view clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == MainActivity.ACTIVITY_REQUEST_CODE)
        {
            //Toast.makeText(this, "Main Act onClick() from Act 2", Toast.LENGTH_SHORT).show();
            if (data != null)
                this.retInfoString = data.getStringExtra("returnString");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        //Toast.makeText(this, this.retInfoString, Toast.LENGTH_SHORT).show();
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
