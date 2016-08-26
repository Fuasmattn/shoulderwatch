package com.lmu.ath.shoulderwatch.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lmu.ath.shoulderwatch.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by daniela on 11.07.16.
 */
public class WearableListAdapter extends WearableListView.Adapter {
    private String[] listData;
    private ArrayList<Drawable> images;
    private final LayoutInflater mInflater;

    // Provide a suitable constructor (depends on the kind of dataset)
    public WearableListAdapter(Context context, String[] dataset, ArrayList<Drawable> images) {
        mInflater = LayoutInflater.from(context);
        this.listData = dataset;
        this.images = images;
    }

    // Provide a reference to the type of views you're using
    public static class ItemViewHolder extends WearableListView.ViewHolder {
        private TextView textView;
        private CircledImageView circledImageView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            // find the text view within the custom item's layout
            textView = (TextView) itemView.findViewById(R.id.name);
            circledImageView = (CircledImageView)
                    itemView.findViewById(R.id.circle);
        }
    }

    // Create new views for list items
    // (invoked by the WearableListView's layout manager)
    @Override
    public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // Inflate our custom layout for list items
        return new ItemViewHolder(mInflater.inflate(R.layout.list_item, null));
    }

    // Replace the contents of a list item
    // Instead of creating new views, the list tries to recycle existing ones
    // (invoked by the WearableListView's layout manager)
    @Override
    public void onBindViewHolder(WearableListView.ViewHolder holder,
                                 int position) {
        // retrieve the text view
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        TextView textView = itemHolder.textView;
        // replace text contents
        textView.setText(listData[position]);
        CircledImageView circledView = itemHolder.circledImageView;
        if (images != null) {
            if (position < images.size()) {
                circledView.setImageDrawable(images.get(position));
            }
        }
        // replace list item's metadata
        holder.itemView.setTag(position);
    }

    // Return the size of your dataset
    // (invoked by the WearableListView's layout manager)
    @Override
    public int getItemCount() {
        return listData.length;
    }

    public Object getItem (int position){
        return listData[position];
    }

}
