package org.bts_netmind.fragmentmanager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

// Make sure this class extends from 'android.app.Fragment' (host activity can extend from 'Activity', API >= 11)
// If it inherits from 'android.support.v4.app.Fragment', the host activity must extend from 'FragmentActivity' (API < 11)
public class FirstFragment extends Fragment implements View.OnClickListener
{
    private static final String TAG_FIRST_FRAGMENT = "In-FirstFragment";

    // Host Activity must implement this interface
    public interface OnFirstFragmentInterface
    {
        void onCommFromFragmentOne(long rNumber);
    }
    private OnFirstFragmentInterface mFirstFragCallback;

    // Required empty public constructor
    public FirstFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onCreateView()");
        // Inflate the layout for this fragment and return it (View)
        View fragView = inflater.inflate(R.layout.fragment_first, container, false);

        final Button activSecondFrag_Btn = (Button) fragView.findViewById(R.id.firstFragmentButton);
            activSecondFrag_Btn.setOnClickListener(this);

        return fragView;
    }

    // This is the actual 'onAttach' callback that should be used from now onwards. However, it it does not work very well with 'Fragment'
    // Try using 'Fragment' from the support library
    /*
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onAttach()");

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        if (context instanceof Activity)
        {
            try
            {
                Activity hostActivity = (Activity) context;
                this.mFirstFragCallback = (OnFirstFragmentInterface) hostActivity;
            }
            catch(ClassCastException e)
            { throw new ClassCastException(this.getActivity().toString() + " must implement OnFirstFragmentInterface"); }
        }
    }
    */

    // Although this method is deprecated in API23, onAttach(Context context) does not work properly yet for previous Android versions

    @Override
    public void onAttach(Activity hostActivity)
    {
        super.onAttach(hostActivity);
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onAttach()");

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try
        { this.mFirstFragCallback = (OnFirstFragmentInterface) hostActivity; }
        catch(ClassCastException e)
        { throw new ClassCastException(this.getActivity().toString() + " must implement OnFirstFragmentInterface"); }
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onActivityCreated()");
    }
    @Override
    public void onStart()
    {
        super.onStart();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onStart()");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onResume()");
    }
    @Override
    public void onPause()
    {
        super.onPause();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onPause()");
    }
    @Override
    public void onStop()
    {
        super.onStop();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onStop()");
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onDestroyView()");
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onDestroy()");
    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "onDetach()");
    }

    @Override
    public void onClick(View viewClicked)
    {
        if (viewClicked.getId() == R.id.firstFragmentButton)
        {
            Log.i(FirstFragment.TAG_FIRST_FRAGMENT, "Button clicked");

            this.mFirstFragCallback.onCommFromFragmentOne(new Random().nextInt(100));
        }
    }
}