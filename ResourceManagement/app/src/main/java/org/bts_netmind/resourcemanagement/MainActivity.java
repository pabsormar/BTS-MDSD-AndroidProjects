package org.bts_netmind.resourcemanagement;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mText = (TextView) this.findViewById(R.id.myTextView);
            //mText.setTextColor(this.getResources().getColor(android.R.color.white, null));

        String abc = this.getResources().getString(R.string.text_view_hello_text);

        Log.i(MainActivity.TAG_MAIN_ACTIVITY, this.getResources().getConfiguration().locale.getLanguage());
    }
}
