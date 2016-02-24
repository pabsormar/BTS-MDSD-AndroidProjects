package org.bts_netmind.fragmentmanager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

// To support fragments, the class has to extend from 'FragmentActivity' for API < 11, 'Activity' otherwise
public class MainActivity extends Activity implements View.OnClickListener, FirstFragment.OnFirstFragmentInterface
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private LinearLayout land_LinLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (this.getResources().getBoolean(R.bool.dual_pane))
        //if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            // Add fragment dynamically
            FragmentManager fragManager = this.getFragmentManager();
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
                // To add a fragment dynamically and modify it later (add, remove, replace), it is required to include a String tag
                // It is sensible to use always 'replace' to avoid fragment overlaps, even if 'onCreate()' is first called
                fragTransaction.replace(R.id.frameLayoutMainLandscape, new FirstFragment(), this.getString(R.string.first_fragment_tag));
                fragTransaction.commit();
            // In one line:
            //this.getFragmentManager().beginTransaction().add(R.id.frameLayoutMainLandscape, new FirstFragment()).commit();

            final Button swapFragment_Btn = (Button) this.findViewById(R.id.btnSwapFragment);
                swapFragment_Btn.setOnClickListener(this);

            // To set up the layout background color, we use 'ContextCompat.getColor()' due to minSDK = 11
            this.land_LinLayout = (LinearLayout) this.findViewById(R.id.linearLayoutMainLandscape);
                this.land_LinLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGreen));
        }
    }

    @Override
    public void onClick(View clickedView)
    {
        if (clickedView.getId() == R.id.btnSwapFragment)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Button clicked");
            // If the button is clicked, the current 'fragment' is replaced
            FragmentManager fragManager = this.getFragmentManager();
            FragmentTransaction fragTransaction = fragManager.beginTransaction();

            // It is important to point out that no ID can be assigned dynamically!
            // Thus, a String argument MUST be used.
            Fragment anyFragment = fragManager.findFragmentByTag(this.getString(R.string.first_fragment_tag));

            if (anyFragment != null)
            {
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Layout 1 is in-layout and will be replaced");
                fragTransaction.replace(R.id.frameLayoutMainLandscape, new SecondFragment(), this.getString(R.string.second_fragment_tag));
                    fragTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragTransaction.commit();
                this.land_LinLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lightYellow));
            }
            else
            {
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Layout 2 is in-layout and will be replaced");
                fragTransaction.replace(R.id.frameLayoutMainLandscape, new FirstFragment(), this.getString(R.string.first_fragment_tag));
                    fragTransaction.commit();
                this.land_LinLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lightGreen));
            }
        }
    }

    // This method is overridden from 'FirstFragment' and it is used to get data from it to the 'MainActivity'
    @Override
    public void onCommFromFragmentOne(long rNumber)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "onCommFromFragmentOne()");

        // A 'first fragment' tagged Fragment will only be present if it was added dynamically, meaning landscape orientation
        //View firstFragment = this.getFragmentManager().findFragmentByTag(this.getString(R.string.first_fragment_tag));
        //    Log.i(MainActivity.TAG_MAIN_ACTIVITY, String.valueOf(firstFragment != null));

        //if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        if (this.getResources().getBoolean(R.bool.dual_pane))     // landscape orientation
        {
            Bundle fragBundle = new Bundle();
                fragBundle.putInt("randomNumberUpdate", (int) rNumber);

            SecondFragment mySecondFragment = new SecondFragment();
                mySecondFragment.setArguments(fragBundle);

            FragmentTransaction myFragTrans = this.getFragmentManager().beginTransaction().replace(R.id.frameLayoutMainLandscape, mySecondFragment, this.getString(R.string.second_fragment_tag));
                myFragTrans.commit();
            this.land_LinLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lightYellow));
        }
        else
        {
            SecondFragment mySecondFragment = (SecondFragment) this.getFragmentManager().findFragmentById(R.id.fragmentSecond);
            mySecondFragment.updateInfoTextView(String.valueOf(rNumber));
        }
    }
}
