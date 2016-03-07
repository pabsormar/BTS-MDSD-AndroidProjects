package org.bts_netmind.menumanager;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener,
                                                                        android.view.ActionMode.Callback,
                                                                        PopupMenu.OnMenuItemClickListener
{
    private static final String TAG_SECOND_ACTIVITY = "In-SecondActivity";

    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar mChildToolbar = (Toolbar) this.findViewById(R.id.my_toolbar_second);
        this.setSupportActionBar(mChildToolbar);
        // Enable the Up button (A Back button, actually)
        // Make sure the corresponding import relates to the suppport v7 library
        ActionBar mActionBar = this.getSupportActionBar();
            mActionBar.setDisplayHomeAsUpEnabled(true);   // This line actually enables the UP button

        final View secondLayout = this.findViewById(R.id.layoutSecondActivity);
            secondLayout.setOnClickListener(this);
        final ImageView icon_ImgView = (ImageView) this.findViewById(R.id.imageViewAppIcon);
            icon_ImgView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.layoutSecondActivity)
        {
            Log.i(SecondActivity.TAG_SECOND_ACTIVITY, "Layout clicked");
            this.finish();
        }
    }

    @Override
    public boolean onLongClick(View whichView)
    {
        if (whichView.getId() == R.id.imageViewAppIcon)
        {
            Log.i(SecondActivity.TAG_SECOND_ACTIVITY, "ImageView long-clicked");
            // if ActionMode is null "not started"
            if (mActionMode != null) { return false; }
            // Start the CAB
            mActionMode = this.startActionMode(this);
            whichView.setSelected(true);
            return true;
        }
        else
            return false;
    }

    // Called when the context menu is first created
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu)
    {
        MenuInflater mMenuInflater = mode.getMenuInflater();
        mMenuInflater.inflate(R.menu.context_menu, menu);
        return true;
    }

    // Nothing is done here, thus 'false' is returned by default
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu)
    {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item)
    {
        if (item.getItemId() == R.id.likeItem)
        {
            Toast.makeText(this, "LIKE item selected", Toast.LENGTH_SHORT).show();
            mode.finish();
            return true;
        }
        else if (item.getItemId() == R.id.dislikeItem)
        {
            Toast.makeText(this, "DISLIKE item selected", Toast.LENGTH_SHORT).show();
            mode.finish();
            return true;
        }
        else if (item.getItemId() == R.id.addItem)
        {
            Toast.makeText(this, "ADD item selected", Toast.LENGTH_SHORT).show();
            // Create a pop-up menu for this option
            PopupMenu mPopMenu = new PopupMenu(this, this.findViewById(R.id.addItem));
            MenuInflater mMenuInflater = mPopMenu.getMenuInflater();
            mMenuInflater.inflate(R.menu.popup_menu, mPopMenu.getMenu());
            // The pop-up menu is configured to be clickable and later is shown
            mPopMenu.setOnMenuItemClickListener(this);
            mPopMenu.show();
            //--------------
            return true;
        }
        else
            return false;
    }

    // Called when the Action mode is destroyed; the 'ActionMode' object is set to null
    @Override
    public void onDestroyActionMode(ActionMode mode) { mActionMode =  null; }

    // This method handles the on-click events on the pop-up menu
    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        if (item.getItemId() == R.id.addOneItem)
        {
            Toast.makeText(this, "ADD-ONE item selected", Toast.LENGTH_SHORT).show();
            if (item.isCheckable())
            {
                if (item.isChecked())
                    item.setChecked(false);
                else
                    item.setChecked(true);
            }
            return true;
        }
        else if (item.getItemId() == R.id.addAllItem)
        {
            Toast.makeText(this, "ADD-ALL item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return false;
    }
}
