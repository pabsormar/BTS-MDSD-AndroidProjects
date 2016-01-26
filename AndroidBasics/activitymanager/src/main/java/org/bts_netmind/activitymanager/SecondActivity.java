package org.bts_netmind.activitymanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends Activity implements View.OnClickListener
{
    private EditText infoReturn_Edt;
    private Button ok_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.infoReturn_Edt = (EditText) this.findViewById(R.id.edtInfoReturn);

        this.ok_Btn = (Button) this.findViewById(R.id.btnOk);
        this.ok_Btn.setOnClickListener(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        //Toast.makeText(this, "Second Act onPause()", Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.btnOk)
        {
            if (this.infoReturn_Edt != null)
            {
                //Toast.makeText(this, this.infoReturn_Edt.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent retIntent = new Intent();
                retIntent.putExtra("returnString", this.infoReturn_Edt.getText().toString());
                setResult(Activity.RESULT_OK, retIntent);
                finish();
            }
        }
    }
}
