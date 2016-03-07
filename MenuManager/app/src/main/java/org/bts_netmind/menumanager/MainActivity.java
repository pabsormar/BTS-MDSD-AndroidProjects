package org.bts_netmind.menumanager;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

// extending from 'AppCompatActivity' allows to use the AppBar (formerly known as 'ActionBar') and the new 'Toolbar'
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private TextView hello_TxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) this.findViewById(R.id.my_toolbar_main);
        this.setSupportActionBar(mToolbar);

        this.hello_TxtView = (TextView) this.findViewById(R.id.txtViewHello);
            this.registerForContextMenu(this.hello_TxtView);
        final View mainLayout = this.findViewById(R.id.layoutMainActivity);
            mainLayout.setOnClickListener(this);
    }

    // This method is overridden to inflate the menu with a layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater mMenuInflater = this.getMenuInflater();
        mMenuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // This method is overridden to provide functionality to menu items when clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Menu item clicked");

        if (item.getItemId() == R.id.copyItem)
        {
            Toast.makeText(this, "COPY item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.cutItem)
        {
            Toast.makeText(this, "CUT item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.pasteItem)
        {
            Toast.makeText(this, "PASTE item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.searchItem)
        {
            Toast.makeText(this, "SEARCH item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.compareItem)
        {
            Toast.makeText(this, "COMPARE item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mContMenuInflater = this.getMenuInflater();
        mContMenuInflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.likeItem)
        {
            Toast.makeText(this, "LIKE context item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.dislikeItem)
        {
            Toast.makeText(this, "DISLIKE context item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (item.getItemId() == R.id.addItem)
        {
            Toast.makeText(this, "ADD context item selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View whichView)
    {
        if (whichView.getId() == R.id.layoutMainActivity)
        {
            Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Layout clicked");
            this.startActivity(new Intent(this, SecondActivity.class));
        }
    }
}
