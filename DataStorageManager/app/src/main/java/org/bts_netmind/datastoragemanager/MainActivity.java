package org.bts_netmind.datastoragemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String MY_SHARED_PREFERENCES = "MySharedPrefsKey";
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    private static final String outputFilename = "internalStorageFile.txt";

    private TextView hello_TxtView;
    private EditText update_EdtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.hello_TxtView = (TextView) this.findViewById(R.id.textViewHello);
        this.update_EdtTxt = (EditText) this.findViewById(R.id.edtTextUpdate);
        final Button ok_Btn = (Button) this.findViewById(R.id.btnOk);
            ok_Btn.setOnClickListener(this);
        final Button internalStorage_Btn = (Button) this.findViewById(R.id.btnInternalStorage);
            internalStorage_Btn.setOnClickListener(this);
        final Button externalStorage_Btn = (Button) this.findViewById(R.id.btnExternalStorage);
            externalStorage_Btn.setOnClickListener(this);

        // Getting the 'SharedPreferences' API and using the default mode (int equal to '0')
        SharedPreferences mSettings = this.getSharedPreferences(MainActivity.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String mString = mSettings.getString("myStringKey", "");
        if (!mString.isEmpty())
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "SharedPreferences available: " + mString);

            this.hello_TxtView.setText(mString);
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "onStop()");

        if (!this.hello_TxtView.getText().toString().equalsIgnoreCase(this.getResources().getString(R.string.default_text)))
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "TextView has been modified");

            SharedPreferences mSettings = this.getSharedPreferences(MainActivity.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor mEditor = mSettings.edit();
            mEditor.putString("myStringKey", this.hello_TxtView.getText().toString());
            mEditor.apply();   // If we want to make sure of the storing process, use commit()
        }
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.btnOk)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Update button clicked");

            if (!this.update_EdtTxt.getText().toString().isEmpty() && !this.update_EdtTxt.getText().toString().matches("^ +"))
                this.hello_TxtView.setText(this.update_EdtTxt.getText().toString());
        }
        else if (whichView.getId() == R.id.btnInternalStorage)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Internal storage button clicked");

            // We will save the 'EditText' content in case it is not empty or it does not consist only of whitespaces
            if (!this.update_EdtTxt.getText().toString().isEmpty() && !this.update_EdtTxt.getText().toString().matches("^ +"))
            {
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Data to be stored on an internal file");

                try
                {
                    FileOutputStream mFileOutputStream = this.openFileOutput(MainActivity.outputFilename, Context.MODE_APPEND);
                    String outputString = this.update_EdtTxt.getText().toString() + "\n";
                    mFileOutputStream.write(outputString.getBytes());
                } catch (IOException e) { Log.i(MainActivity.TAG_MAIN_ACTIVITY, e.getMessage()); }
            }

        }
        else if (whichView.getId() == R.id.btnExternalStorage)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "External storage button clicked");

        }
    }
}
