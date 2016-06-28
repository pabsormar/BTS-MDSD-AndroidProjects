package org.bts_netmind.tddmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener
{
    public static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mTextView = (TextView) this.findViewById(R.id.textView);

        this.mEditText = (EditText) this.findViewById(R.id.editText);

        final Button mButton = (Button) this.findViewById(R.id.button);
            mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View whichView)
    {
        if(whichView.getId() == R.id.button)
        {
            this.mTextView.setText("Hello, " + this.mEditText.getText().toString() + "!");
        }
    }
}
