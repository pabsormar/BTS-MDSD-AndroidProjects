package org.bts_netmind.dataadaptermanager;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityWithList extends ListActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // This 'String[]' data could be retrieved from a file, for instance
        String[] mListValues = {"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        //this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListValues));
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, mListValues));
        this.getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(this, "Element " + position + ", with ID = " + id, Toast.LENGTH_SHORT).show();
    }
}
