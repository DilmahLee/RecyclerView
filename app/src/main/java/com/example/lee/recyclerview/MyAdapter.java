package com.example.lee.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    //private ItemData[] itemsData;
    protected ArrayList<ItemData> Carrylist;
    //OnItemClickListener onItemClickListener;
   // private OnRecyclerViewItemClickListener mOnItemClickListener =  null ;
   // OnItemRecycleViewClickListener mOnItemRecycleViewClickListener;

    //,OnItemRecycleViewClickListener mOnItemRecycleViewClickListener
    public MyAdapter(ArrayList<ItemData> Carrylist) {
        //this.itemsData = itemsData;
        this.Carrylist = Carrylist;
        //this.mOnItemRecycleViewClickListener = mOnItemRecycleViewClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        //itemLayoutView.setOnClickListener(this);
        return viewHolder;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        final int pos = position;
        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData

        // viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        //viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageUrl());
        viewHolder.txtViewTitle.setText(Carrylist.get(position).getTitle());
        viewHolder.imgViewIcon.setImageResource(Carrylist.get(position).getImageUrl());
        /*viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemRecycleViewClickListener.onItemClicked(position,MyAdapter.this);
            }
        });*/
    }

    // inner class to hold a reference to each item of RecyclerView 
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.item_icon);


        }
    }




    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return itemsData.length;
        return Carrylist.size();
    }
       //自定義點擊實作
   /* public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener  = listener;
    }*/


   /* public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this .mOnItemClickListener = listener;
    }*/

}