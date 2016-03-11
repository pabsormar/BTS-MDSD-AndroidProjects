package org.bts_netmind.datastoragemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    private static final String MY_SHARED_PREFERENCES = "MySharedPrefsKey";
    private static final String outputFilename = "internalStorageFile.txt";
    //private static final String outputFilename = "internalStorageFile2.txt";

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
        final Button getFiles_Btn = (Button) this.findViewById(R.id.btnGetFiles);
            getFiles_Btn.setOnClickListener(this);
        final Button createDB_Btn = (Button) this.findViewById(R.id.btnDataBase);
            createDB_Btn.setOnClickListener(this);

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
                    mFileOutputStream.close();
                } catch (IOException e) { Log.i(MainActivity.TAG_MAIN_ACTIVITY, e.getMessage()); }
            }

        }
        else if (whichView.getId() == R.id.btnExternalStorage)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "External storage button clicked");

            // Check whether the external memory is readable and writable
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, String.valueOf(this.isExternalStorageReadable()));
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, String.valueOf(this.isExternalStorageWritable()));

            // Creating a new directory in the 'Downloads' public directory
            File downloadFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "newDirectory/newSubDirectory");

            if (!downloadFolder.mkdirs())
                Toast.makeText(this, "Directory not created", Toast.LENGTH_SHORT).show();

            Log.i(MainActivity.TAG_MAIN_ACTIVITY, this.getExternalFilesDir(null).toString());
        }
        else if (whichView.getId() == R.id.btnGetFiles)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Get files button clicked");

            for (String aString : this.fileList() )
            {
                Toast.makeText(this, aString, Toast.LENGTH_SHORT).show();
            }
        }
        else if (whichView.getId() == R.id.btnDataBase)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Create DB button clicked");

            MyDatabase mDB = new MyDatabase(this);
            SQLiteDatabase s = mDB.getReadableDatabase();
        }
    }

    // Checks if external storage is available for read and write
    public boolean isExternalStorageWritable()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            return true;
        }
        return false;
    }

    // Checks if external storage is available to at least read
    public boolean isExternalStorageReadable()
    {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            return true;
        }
        return false;
    }

    private class MyDatabase extends SQLiteOpenHelper
    {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "My_personal_database";
        private static final String TABLE_NAME = "A_DB_table";
        private static final String KEY_WORD = "Term";
        private static final String KEY_DESCRIPTION = "Description";
        private static final String DATABASE_CREATE_COMMAND = "CREATE TABLE " + MyDatabase.TABLE_NAME
                    + " (" + MyDatabase.KEY_WORD + " TEXT, "
                    + MyDatabase.KEY_DESCRIPTION + " TEXT);";

        // This constructor needs to be declared to provide parameters to the superclass
        public MyDatabase(Context context)
        {
            super(context, MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(MyDatabase.DATABASE_CREATE_COMMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
