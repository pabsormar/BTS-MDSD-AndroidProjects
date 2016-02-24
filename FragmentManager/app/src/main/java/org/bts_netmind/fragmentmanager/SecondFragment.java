package org.bts_netmind.fragmentmanager;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment
{
    private static final String TAG_SECOND_FRAGMENT = "In-SecondFragment";

    private TextView info_TxtView;

    public SecondFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(SecondFragment.TAG_SECOND_FRAGMENT, "onCreateView()");
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_second, container, false);
        // Get a reference for the class' TextView field
        this.info_TxtView = (TextView) fragView.findViewById(R.id.txtViewFragment2);

        return fragView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // Any GUI element must be updated/modified once the host Activity is finally created
        if (this.getArguments() != null)
        {
            Log.i(SecondFragment.TAG_SECOND_FRAGMENT, "onActivityCreated() by getArguments()");
            this.info_TxtView.setText(String.valueOf(this.getArguments().getInt("randomNumberUpdate")));
        }

        if (savedInstanceState != null)
        {
            Log.i(SecondFragment.TAG_SECOND_FRAGMENT, "onActivityCreated() by savedInstanceState()");
            this.info_TxtView.setText(savedInstanceState.getString("currentString"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.i(SecondFragment.TAG_SECOND_FRAGMENT, "onSaveInstanceState()");

        if (!this.info_TxtView.getText().toString().equals(this.getString(R.string.txt_view_second_fragment_text)))
        {
            Log.i(SecondFragment.TAG_SECOND_FRAGMENT, "TextView changed!");
            outState.putString("currentString", this.info_TxtView.getText().toString());
        }
    }

    public void updateInfoTextView(String newString)
    {
        final TextView info_TxtView = (TextView) this.getView().findViewById(R.id.txtViewFragment2);
            info_TxtView.setText(newString);
    }
}
