package org.bts_netmind.tddmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditText = (EditText) findViewById(R.id.editText);

        final Button mButton = (Button) findViewById(R.id.button);
            mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View whichView) {
        int viewId = whichView.getId();

        if(viewId == R.id.button) {
            mTextView.setText("Hello, " + mEditText.getText().toString() + "!");
        }
    }
}
