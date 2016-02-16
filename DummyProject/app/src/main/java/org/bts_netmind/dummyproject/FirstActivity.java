package org.bts_netmind.dummyproject;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;

public class FirstActivity extends Activity
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

    }
}
