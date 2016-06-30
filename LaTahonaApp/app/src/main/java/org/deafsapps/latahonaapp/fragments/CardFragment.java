package org.deafsapps.latahonaapp.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardFragment extends Fragment
{
    private ArrayList<String> mList = new ArrayList<>();

    // Required empty public constructor
    public CardFragment()
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
        CardContentAdapter mContAdapter = new CardContentAdapter(this.getContext(), this.mList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(mContAdapter);

        return recyclerView;
    }

    // Adapter to display recycler view
    private class CardContentAdapter extends RecyclerView.Adapter<CardContentAdapter.MyCardViewHolder>
    {
        Context mContext;
        ArrayList<String> itemList;

        // Creating a 'ViewHolder' to speed up the performance
        public class MyCardViewHolder extends RecyclerView.ViewHolder
        {
            public TextView title_TxtView;

            public MyCardViewHolder(View itemView)
            {
                super(itemView);

                this.title_TxtView = (TextView) itemView.findViewById(R.id.card_title);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public CardContentAdapter(Context context, ArrayList<String> objects)
        {
            this.mContext = context;
            this.itemList = objects;
        }

        @Override
        public MyCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            // create a new view
            View viewRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_custom_layout, parent, false);
            viewRow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) { Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show(); }
            });
            // set the view's size, margins, paddings and layout parameters

            return new MyCardViewHolder(viewRow);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyCardViewHolder holder, int position)
        {
            // get element from your dataset at this position
            // replace the contents of the view with that element
            holder.title_TxtView.setText(this.itemList.get(position));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() { return this.itemList.size(); }
    }
}
