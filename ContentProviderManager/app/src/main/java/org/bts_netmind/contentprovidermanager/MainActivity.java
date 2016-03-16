package org.bts_netmind.contentprovidermanager;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private EditText name_EdtTxt;
    private EditText age_EdtTxt;
    private Button queryDB_Btn;
    private Button addRegister_Btn;
    private Button deleteRegister_Btn;
    private MyContentProvider myProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.queryDB_Btn = (Button) this.findViewById(R.id.btnQueryDB);
            this.queryDB_Btn.setOnClickListener(this);
        this.addRegister_Btn = (Button) this.findViewById(R.id.btnAddDB);
            this.addRegister_Btn.setOnClickListener(this);
            this.addRegister_Btn.setEnabled(false);
        this.deleteRegister_Btn = (Button) this.findViewById(R.id.btnDeleteDB);
            this.deleteRegister_Btn.setOnClickListener(this);
            this.deleteRegister_Btn.setEnabled(false);
        this.name_EdtTxt = (EditText) this.findViewById(R.id.edtTxtName);
            this.name_EdtTxt.addTextChangedListener(this);
        this.age_EdtTxt = (EditText) this.findViewById(R.id.edtTxtAge);
            this.age_EdtTxt.addTextChangedListener(this);

        // Instantiate the custom 'ContentProvider'; the constructor actually creates the database
        this.myProvider = new MyContentProvider();
    }

    @Override
    public void onClick(View whichView)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Button clicked");

        if (whichView.getId() == R.id.btnQueryDB)
        {
            String[] columnsToQuery = {ParamsDB.STUDENT_NAME, ParamsDB.STUDENT_AGE};
            Cursor aCursor = this.getContentResolver().query(MyContentProvider.CONTENT_URI, columnsToQuery, null, null, null);

            // Once queried, the result ('Cursor' object) will be parsed into a String and showed on a 'Toast'
            StringBuilder dbContent = new StringBuilder("");

            while (aCursor.moveToNext())
            { dbContent.append("\n" + String.valueOf(aCursor.getString(0)) + " " + String.valueOf(aCursor.getInt(1)) + "\n"); }

            if (dbContent.toString().trim().length() > 0)
            { Toast.makeText(this, dbContent.toString(), Toast.LENGTH_LONG).show(); }
            else
            { Toast.makeText(this, "Empty database", Toast.LENGTH_LONG).show(); }

            // Always close the 'Cursor'
            aCursor.close();
        }
        else if (whichView.getId() == R.id.btnAddDB)
        {
            ContentValues insertValues = new ContentValues();
                insertValues.put(ParamsDB.STUDENT_NAME, this.name_EdtTxt.getText().toString());
                insertValues.put(ParamsDB.STUDENT_AGE, Integer.valueOf(this.age_EdtTxt.getText().toString()));

            Uri mUri = this.getContentResolver().insert(MyContentProvider.CONTENT_URI, insertValues);

            // Clean the 'EditText' to improve UX
            this.name_EdtTxt.setText("");
            this.age_EdtTxt.setText("");

            Toast.makeText(this, "New register added", Toast.LENGTH_SHORT).show();
        }
        else if (whichView.getId() == R.id.btnDeleteDB)
        {
            String whereCond = ParamsDB.STUDENT_NAME + "=? AND " + ParamsDB.STUDENT_AGE + "=?";
            String [] whereCondArgs = {this.name_EdtTxt.getText().toString(), this.age_EdtTxt.getText().toString()};

            int deletedRows = this.getContentResolver().delete(MyContentProvider.CONTENT_URI, whereCond, whereCondArgs);

            // Clean the 'EditText' to improve UX
            this.name_EdtTxt.setText("");
            this.age_EdtTxt.setText("");

            Toast.makeText(this, "Deleted rows: " + String.valueOf(deletedRows), Toast.LENGTH_SHORT).show();
        }
    }

    // The following methods check the content of the 'EditText', enabling them if both are not empty
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "onTextChanged");

        if (this.name_EdtTxt.getText().toString().trim().length() > 0 && this.age_EdtTxt.getText().toString().trim().length() > 0)
        {
            this.addRegister_Btn.setEnabled(true);
            this.deleteRegister_Btn.setEnabled(true);
        }
        else
        {
            this.addRegister_Btn.setEnabled(false);
            this.deleteRegister_Btn.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) { }
}
