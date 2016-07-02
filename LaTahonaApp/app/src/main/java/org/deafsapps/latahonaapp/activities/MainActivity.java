package org.deafsapps.latahonaapp.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.deafsapps.latahonaapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private CoordinatorLayout mCoordLayout;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This 'CoordinatorLayout' reference is used to display 'Snackbar' messages (employed as 'View' object)
        this.mCoordLayout = (CoordinatorLayout) this.findViewById(R.id.appCoordLayout);

        //----- DRAWERLAYOUT -----
        // The following lines link the 'DrawerLayout' object and the 'NavigationView' object and their behaviours
        this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.appDrawerLayout);
        NavigationView mNavigationView = (NavigationView) this.findViewById(R.id.appNavView);
            // This next line makes 'NavigationView' items react to interaction (defined in 'onNavigationItemSelected' method)
            mNavigationView.setNavigationItemSelectedListener(this);
        //--------------------------------------

        //----- TOOLBAR -----
        // Getting a reference to the 'Toolbar' and adding it as ActionBar for the 'Activity'
        final Toolbar mToolbar = (Toolbar) this.findViewById(R.id.appToolbar);
        // This coming line maked the magic, replacing the 'ActionBar' with the 'Toolbar'
        this.setSupportActionBar(mToolbar);

        final ActionBar mActionBar = this.getSupportActionBar();
        if (mActionBar != null)
        {
            // Allows to use another 'drawable' on the 'ActionBar' (in the 'Toolbar' in this case)
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);   // Corresponds to the 'Hamburger' icon
            // This line actually shows a 'Button' on the top left corner of the 'ActionBar'/'Toolbar',
            // whose behaviour can be defined through the 'onOptionsItemSelected' method
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        //--------------------------------------
    }

    @Override
    public void onClick(View whichView)
    {


    }

    // Since the 'Toolbar' is enabled and working with the 'ActionBar', this method is called when the 'Hamburger' or a Toolbar option is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // The 'android.R.id.home' identifier refers to the 'Hamburger'
        if (item.getItemId() == android.R.id.home)
        {
            this.mDrawerLayout.openDrawer(GravityCompat.START);
        }
        else
            return false;

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        return false;
    }
}
