package org.bts_netmind.dataadaptermanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
                                                        AdapterView.OnItemLongClickListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        // Referencing the 'ListView' and adding 'OnItemClickListener' behaviour to it
        final ListView mListView = (ListView) this.findViewById(R.id.listViewMain);
            mListView.setOnItemClickListener(this);
            mListView.setOnItemLongClickListener(this);

        // This 'String[]' data could be retrieved from a file, for instance
        String[] mListValues = {"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        // Standard definition to populate a 'ListView' with an 'ArrayList<String>' ----------------

        ArrayList<String> mListArray = new ArrayList<>();
        // Just populating the 'ArrayList' with the 'String[]'
        for (String aString : mListValues)
        {
            mListArray.add(aString);
        }

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListArray));

        //------------------------------------------------------------------------------------------
        /*
        // Using a custom adapter for the 'ListView' -----------------------------------------------
        // 'ArrayList' with image references (DO NOT INCLUDE FILE EXTENSIONS!)
        String[] mListImages = {"androidicon", "iphoneicon", "windowsmobileicon",
                "blackberryicon", "webosicon", "ubuntuicon", "win7icon", "macosxicon",
                "linuxicon", "os2icon", "ubuntuicon", "win7icon", "macosxicon", "linuxicon",
                "os2icon", "ubuntuicon", "win7icon", "macosxicon", "linuxicon", "os2icon",
                "androidicon", "iphoneicon", "windowsmobileicon"};

        ArrayList<Item> mListArray = new ArrayList<>();
        // Just populating the 'ArrayList' with the 'Item[]'
        for (int idx = 0; idx < mListValues.length; idx++)
            mListArray.add(new Item(mListImages[idx], mListValues[idx], "A body describing the element in the list"));

            //--------------------------------------
            // Adding a 'header' to the 'ListView'.  It has to be done BEFORE the adapter is assigned
            View listHeader = ((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_view_header, mListView, false);
            mListView.addHeaderView(listHeader, null, true);
            //---------------------------------------

        mListView.setAdapter(new MyListAdapter(this, 0, mListArray));
        */
        //------------------------------------------------------------------------------------------
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Element " + position + ", with ID = " + id);
        Toast.makeText(this, "Element " + position + ", with ID = " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Element " + position + ", with ID = " + id + "long-clicked");
        Toast.makeText(this, "Element " + position + ", with ID = " + id + " long-clicked", Toast.LENGTH_SHORT).show();

        return true;
    }

    // That adapter has a constructor and a 'getView()' method to describe the translation between the data item and the View to display
    // 'getView()' is the method that returns the actual view used as a row within the 'ListView' at a particular position
    private class MyListAdapter extends ArrayAdapter<Item>
    {
        Context mContext;
        ArrayList<Item> itemList;

        public MyListAdapter(Context context, int resource, ArrayList<Item> objects)
        {
            super(context, resource, objects);

            this.mContext = context;
            this.itemList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            LayoutInflater mInflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_view_custom_layout, null);

            ImageView listRow_ImgView = (ImageView) convertView.findViewById(R.id.imageViewList);
                listRow_ImgView.setImageResource(this.mContext.getResources().getIdentifier(this.itemList.get(position).getmImageRef(), "drawable", this.mContext.getPackageName()));
            TextView listRowTitle_TxtView = (TextView) convertView.findViewById(R.id.textViewTiltle);
                listRowTitle_TxtView.setText(this.itemList.get(position).getmTitle());
            TextView listRowBody_TxtView = (TextView) convertView.findViewById(R.id.textViewBody);
                listRowBody_TxtView.setText(this.itemList.get(position).getmBody());

            // To check that views are loaded only when they have to be shown
            //Log.i(MainActivity.TAG_MAIN_ACTIVITY, String.valueOf(this.mContext.getResources().getIdentifier(this.itemList.get(position).getmImageRef(), "drawable", this.mContext.getPackageName())));

            return convertView;
        }
    }
}
