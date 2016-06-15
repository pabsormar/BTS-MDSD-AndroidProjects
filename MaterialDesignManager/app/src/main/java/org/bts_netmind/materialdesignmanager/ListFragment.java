package org.bts_netmind.materialdesignmanager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListFragment extends Fragment
{
    private ArrayList<Item> mList = new ArrayList<Item>();

    // Required empty public constructor
    public ListFragment()
    {
        // This 'String[]' data could be retrieved from a file, for instance
        String[] mListValues = {"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        // 'ArrayList' with image references (DO NOT INCLUDE FILE EXTENSIONS!)
        String[] mListImages = {"androidicon", "iphoneicon", "windowsmobileicon",
                "blackberryicon", "webosicon", "ubuntuicon", "win7icon", "macosxicon",
                "linuxicon", "os2icon", "ubuntuicon", "win7icon", "macosxicon", "linuxicon",
                "os2icon", "ubuntuicon", "win7icon", "macosxicon", "linuxicon", "os2icon",
                "androidicon", "iphoneicon", "windowsmobileicon"};

        // Just populating the 'ArrayList' with the 'Item[]'
        for (int idx = 0; idx < mListValues.length; idx++)
            this.mList.add(new Item(mListImages[idx], mListValues[idx], "A body describing the element in the list"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view_layout, container, false);
        ContentAdapter mContAdapter = new ContentAdapter(this.getContext(), this.mList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(mContAdapter);

        return recyclerView;
    }

    // Adapter to display recycler view
    private class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder>
    {
        Context mContext;
        ArrayList<Item> itemList;

        // Creating a 'ViewHolder' to speed up the performance
        public class MyViewHolder extends RecyclerView.ViewHolder
        {
            public ImageView icon_ImgView;
            public TextView title_TxtView;
            public TextView body_TxtView;

            public MyViewHolder(View itemView)
            {
                super(itemView);

                this.icon_ImgView = (ImageView) itemView.findViewById(R.id.imageViewList);
                this.title_TxtView = (TextView) itemView.findViewById(R.id.textViewTiltle);
                this.body_TxtView = (TextView) itemView.findViewById(R.id.textViewBody);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ContentAdapter(Context context, ArrayList<Item> objects)
        {
            this.mContext = context;
            this.itemList = objects;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            View viewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_custom_layout, parent, false);
            viewRow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) { Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show(); }
            });
            // set the view's size, margins, paddings and layout parameters
            MyViewHolder viewRowHolder = new MyViewHolder(viewRow);

            return viewRowHolder;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            // get element from your dataset at this position
            // replace the contents of the view with that element
            //holder.icon_ImgView.setImageResource(this.mContext.getResources().getIdentifier(this.itemList.get(position).getmImageRef(), "drawable", this.mContext.getPackageName()));
            holder.title_TxtView.setText(this.itemList.get(position).getmTitle());
            holder.body_TxtView.setText(this.itemList.get(position).getmBody());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() { return this.itemList.size(); }
    }
}
