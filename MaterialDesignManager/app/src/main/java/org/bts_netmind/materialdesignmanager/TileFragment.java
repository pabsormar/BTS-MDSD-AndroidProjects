package org.bts_netmind.materialdesignmanager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TileFragment extends Fragment
{
    private ArrayList<String> mList = new ArrayList<>();

    // Required empty public constructor
    public TileFragment()
    {
        // This 'String[]' data could be retrieved from a file, for instance
        String[] mListValues = {"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        // Just populating the 'ArrayList' with the 'String[]'
        for (String aString : mListValues)
            this.mList.add(aString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view_layout, container, false);
        TileContentAdapter mContAdapter = new TileContentAdapter(this.getContext(), this.mList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.setAdapter(mContAdapter);

        return recyclerView;
    }

    // Adapter to display recycler view
    private class TileContentAdapter extends RecyclerView.Adapter<TileContentAdapter.MyTileViewHolder>
    {
        Context mContext;
        ArrayList<String> itemList;

        // Creating a 'ViewHolder' to speed up the performance
        public class MyTileViewHolder extends RecyclerView.ViewHolder
        {
            public ImageView icon_ImgView;
            public TextView desc_TxtView;

            public MyTileViewHolder(View itemView)
            {
                super(itemView);

                this.icon_ImgView = (ImageView) itemView.findViewById(R.id.imageViewTile);
                this.desc_TxtView = (TextView) itemView.findViewById(R.id.textViewTile);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public TileContentAdapter(Context context, ArrayList<String> objects)
        {
            this.mContext = context;
            this.itemList = objects;
        }

        @Override
        public MyTileViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            View viewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_view_custom_layout, parent, false);
            viewRow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) { Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show(); }
            });
            // set the view's size, margins, paddings and layout parameters

            return new MyTileViewHolder(viewRow);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyTileViewHolder holder, int position)
        {
            // get element from your dataset at this position
            // replace the contents of the view with that element
            holder.icon_ImgView.setImageResource(R.mipmap.ic_launcher);
            holder.desc_TxtView.setText(this.itemList.get(position));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() { return this.itemList.size(); }
    }
}
