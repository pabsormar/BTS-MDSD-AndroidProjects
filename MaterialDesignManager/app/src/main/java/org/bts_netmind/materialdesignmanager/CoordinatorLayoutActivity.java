package org.bts_netmind.materialdesignmanager;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorLayoutActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener,
                                                                                TabLayout.OnTabSelectedListener
{
    private static final String TAG_COORDINATOR_LAYOUT_ACTIVITY = "In-CoordinatorLayoutActivity";

    private CoordinatorLayout mCoordLayout;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);

        this.mCoordLayout = (CoordinatorLayout) this.findViewById(R.id.mainCoordLayout);

        // The following lines link the 'DrawerLayout' object and the 'NavigationView' object and their behaviours
        this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.coordLayoutDrawerLayout);
        NavigationView appSideMenu_NavigationView = (NavigationView) this.findViewById(R.id.coordLayoutNavView);
            // This next line makes 'NavigationView' items react to interaction (defined in 'onNavigationItemSelected' method)
            appSideMenu_NavigationView.setNavigationItemSelectedListener(this);

        // A 'ViewPager' object allows to include swipe gesture to move across pages or fragments
        this.mViewPager = (ViewPager) this.findViewById(R.id.coordLayoutViewPager);
        MyPageAdapter mPageAdapter = new MyPageAdapter(this.getSupportFragmentManager());
            mPageAdapter.addFragment(new ListFragment(), "List");
            mPageAdapter.addFragment(new TileFragment(), "Tile");
            mPageAdapter.addFragment(new CardFragment(), "Card");
        this.mViewPager.setAdapter(mPageAdapter);

        // Getting a reference to the 'TabLayout' to add 'Tab' elements through a 'ViewPager' object
        final TabLayout app_TabLayout = (TabLayout) this.findViewById(R.id.coordLayoutTabLayout);
        // 'setupWithViewPager' requires to override the 'getPageTitle' method from the 'FragmentPageAdapter' class
        // The return value of the latter must be a List or array with the titles of the distinct tabs
        app_TabLayout.setupWithViewPager(this.mViewPager);

        // Getting a reference to the 'Toolbar' and adding it as ActionBar for the 'Activity'
        final Toolbar coordLayout_Toolbar = (Toolbar) this.findViewById(R.id.coordLayoutToolbar);
        // This coming line maked the magic, replacing the 'ActionBar' with the 'Toolbar'
        this.setSupportActionBar(coordLayout_Toolbar);

        final ActionBar mActionBar = this.getSupportActionBar();
        if (mActionBar != null)
        {
            // Allows to use another 'drawable' on the 'ActionBar' (in the 'Toolbar' in this case)
            mActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);   // Corresponds to the 'Hamburger' icon
            // This line actually shows a 'Button' on the top left corner of the 'ActionBar'/'Toolbar'
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        final FloatingActionButton fab_Btn = (FloatingActionButton) this.findViewById(R.id.fabButton);
            fab_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.fabButton)
        {
            Snackbar.make(this.mCoordLayout, "FAB clicked", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mInflater = this.getMenuInflater();
        mInflater.inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
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
        else if (item.getItemId() == R.id.likeOption)
        {
            Snackbar.make(this.mCoordLayout, "Liked!", Snackbar.LENGTH_SHORT).show();
        }
        else
            return false;

        return super.onOptionsItemSelected(item);
    }

    // This method will trigger on item Click of navigation menu and defines what happens later
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Item is checked
        item.setChecked(true);

        // Closing drawer on item click
        this.mDrawerLayout.closeDrawers();
        return true;
    }

    // The following methods correspond to 'TabLayout.OnTabSelectedListener'
    @Override
    public void onTabSelected(TabLayout.Tab tab) { this.mViewPager.setCurrentItem(tab.getPosition()); }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }

    // This 'FragmentPageAdapter' instance will be used with the 'ViewPager' object
    private class MyPageAdapter extends FragmentPagerAdapter
    {
        private FragmentManager mManager;
        private List<Fragment> mFragmentList = new ArrayList<Fragment>();
        private List<String> mFragmentTitleList = new ArrayList<String>();

        public MyPageAdapter(FragmentManager mFragManager)
        {
            super(mFragManager);
            this.mManager = mFragManager;
        }

        public void addFragment(Fragment aFragment, String aFragTitle)
        {
            this.mFragmentList.add(aFragment);
            this.mFragmentTitleList.add(aFragTitle);
        }

        @Override
        public Fragment getItem(int position) { return this.mFragmentList.get(position); }

        public String getFragTitle(int position) { return this.mFragmentTitleList.get(position); }

        @Override
        public int getCount() { return this.mFragmentList.size(); }

        @Override
        public CharSequence getPageTitle(int position) { return this.mFragmentTitleList.get(position); }

        public List<Fragment> getFragmentList() { return mFragmentList; }

        public List<String> getFragmentTitleList() { return mFragmentTitleList; }
    }
}
