package org.bts_netmind.dataadaptermanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityOptimizedRecyclerView extends AppCompatActivity
{
    private static final String TAG_ACTIVITY_OPTIMIZED_RECYCLER_VIEW = "In-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main_with_recycler_view);

        // Referencing the 'RecyclerView'
        final RecyclerView mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerViewMain);

        // This 'String[]' data could be retrieved from a file, for instance
        String[] mListValues = {"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        //------------------------------------------------------------------------------------------

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the 'RecyclerView'
        mRecyclerView.setHasFixedSize(true);
        // Define now how children are organised (in 'LinearLayout', in a 'GridLayout', or in a 'StaggeredLinearLayout'
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.HORIZONTAL, false));
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(new MyListAdapterRecycler(this, mListArray));

        //------------------------------------------------------------------------------------------
    }

    // This adapter uses the 'RecyclerView.Adapter' for the 'RecyclerView' (which is an optimized 'ListView')
    private class MyListAdapterRecycler extends RecyclerView.Adapter<MyListAdapterRecycler.ViewHolder>
    {
        // Creating a 'ViewHolder' to speed up the performance
        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public ImageView icon_ImgView;
            public TextView title_TxtView;
            public TextView body_TxtView;

            public ViewHolder(View itemView)
            {
                super(itemView);

                this.icon_ImgView = (ImageView) itemView.findViewById(R.id.imageViewList);
                this.title_TxtView = (TextView) itemView.findViewById(R.id.textViewTiltle);
                this.body_TxtView = (TextView) itemView.findViewById(R.id.textViewBody);
            }
        }

        Context mContext;
        ArrayList<Item> itemList;

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyListAdapterRecycler(Context context, ArrayList<Item> objects)
        {
            this.mContext = context;
            this.itemList = objects;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType)
        {
            // create a new view
            View viewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_custom_layout, parent, false);
            viewRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
            // set the view's size, margins, paddings and layout parameters
            ViewHolder viewRowHolder = new ViewHolder(viewRow);
            return viewRowHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            // get element from your dataset at this position
            // replace the contents of the view with that element
            holder.icon_ImgView.setImageResource(this.mContext.getResources().getIdentifier(this.itemList.get(position).getmImageRef(), "drawable", this.mContext.getPackageName()));
            holder.title_TxtView.setText(this.itemList.get(position).getmTitle());
            holder.body_TxtView.setText(this.itemList.get(position).getmBody());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount()
        {
            return this.itemList.size();
        }
    }
}
