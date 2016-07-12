package com.lmu.ath.shoulderwatch.ui.fragments;


import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.ui.activities.SelectionsActivity;
import com.lmu.ath.shoulderwatch.ui.adapter.WearableListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectableListFragment extends Fragment implements WearableListView.ClickListener{


    public static SelectableListFragment newInstance(String type) {
        SelectableListFragment selectableListFragment = new SelectableListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        selectableListFragment.setArguments(bundle);
        return selectableListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_environment, container, false);

       // WearableListView listView = (WearableListView) view.findViewById(R.id.wearable_list);
        WearableListView listView = (WearableListView) view.findViewById(R.id.wearable_list);

        String type = getArguments().getString("type");

        //TODO: add correct icons
        ArrayList<Drawable> environmentIcons = new ArrayList<Drawable>();
        environmentIcons.add(getResources().getDrawable(R.mipmap.metro, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.metro, null));

        Context context = getActivity();
        WearableListAdapter wearableListAdapter = null;

        if (type.equals(SelectionsActivity.ENVIRONMENT)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.environment), environmentIcons);

        } else if (type.equals(SelectionsActivity.DEVICETYPE)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.devicetype), environmentIcons);

        } else if (type.equals(SelectionsActivity.DEVICEANALOG)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.device_analog), environmentIcons);

        } else if (type.equals(SelectionsActivity.DEVICEDIGITAL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.device_digital), environmentIcons);

        } else if (type.equals(SelectionsActivity.CONTENT)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.content), environmentIcons);
        }

        if (wearableListAdapter != null){
            listView.setAdapter(wearableListAdapter);
        }

        listView.setGreedyTouchMode(true);

        // Set a click listener
        listView.setClickListener(this);

        return view;
    }

    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {
            Integer tag = (Integer) v.itemView.getTag();
            // use this data to complete some action ...
            }

    @Override
    public void onTopEmptyRegionClick() {

    }

}
