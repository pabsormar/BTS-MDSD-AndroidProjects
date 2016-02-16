package org.bts_netmind.guidesign;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.jar.Attributes;

public class FirstActivity extends Activity
{
    RelativeLayout mRelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        this.mRelLayout = (RelativeLayout) this.findViewById(R.id.relLayout);
        Button mButton = new Button(this);
        this.mRelLayout.addView(mButton);
    }
}
