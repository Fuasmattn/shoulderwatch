package com.lmu.ath.shoulderwatch.ui.fragments;


import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.database.DataManager;
import com.lmu.ath.shoulderwatch.database.ShoulderWatchTable;
import com.lmu.ath.shoulderwatch.ui.activities.SelectionsActivity;
import com.lmu.ath.shoulderwatch.ui.adapter.WearableListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectableListFragment extends Fragment implements WearableListView.ClickListener{


    private WearableListAdapter wearableListAdapter = null;
    private String listType;
    private DataManager dataManager;
    private WearableListView listView;

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
        listView = (WearableListView) view.findViewById(R.id.wearable_list);

        listType = getArguments().getString("type");

        dataManager = DataManager.getInstance();

        //TODO: add correct icons
        ArrayList<Drawable> environmentIcons = new ArrayList<Drawable>();
        environmentIcons.add(getResources().getDrawable(R.mipmap.metro, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.place, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.building, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.event, null));

        ArrayList<Drawable> typeIcons = new ArrayList<Drawable>();
        typeIcons.add(getResources().getDrawable(R.mipmap.analog, null));
        typeIcons.add(getResources().getDrawable(R.mipmap.digital, null));

        Context context = getActivity();


        if (listType.equals(SelectionsActivity.ENVIRONMENT)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.environment), environmentIcons);

        } else if (listType.equals(SelectionsActivity.DEVICETYPE)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.devicetype), typeIcons);

        } else if (listType.equals(SelectionsActivity.DEVICEANALOG)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.device_analog), environmentIcons);

        } else if (listType.equals(SelectionsActivity.DEVICEDIGITAL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.device_digital), environmentIcons);

        } else if (listType.equals(SelectionsActivity.CONTENT)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.content), environmentIcons);

        } else if (listType.equals(SelectionsActivity.SURFRATING)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.surf_rating), environmentIcons);

        }else if (listType.equals(SelectionsActivity.RELATIVE_POSITION)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.relative_position), environmentIcons);

        }else if (listType.equals(SelectionsActivity.CROWD_LEVEL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.crowd_density), environmentIcons);

        }else if (listType.equals(SelectionsActivity.DEFENCE_LEVEL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.defence_level), environmentIcons);
        }

        if (wearableListAdapter != null){
            listView.setAdapter(wearableListAdapter);
        }


        // Set a click listener
        listView.setClickListener(this);

        return view;
    }

    // WearableListView click listener
    @Override
    public void onClick(WearableListView.ViewHolder v) {
        Integer tag = (Integer) v.itemView.getTag();

        //on button press, save data to SQLite Database
        ContentValues values = new ContentValues();

        if (listType.equals(SelectionsActivity.ENVIRONMENT)){
            String item = (String) wearableListAdapter.getItem(tag);
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_ENVIRONMENT, item);

        } else if (listType.equals(SelectionsActivity.DEVICETYPE)){
            String item = (String) wearableListAdapter.getItem(tag);
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEVICETYPE, item);

        } else if (listType.equals(SelectionsActivity.DEVICEANALOG)){
            String item = (String) wearableListAdapter.getItem(tag);
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEVICE_ANALOG, item);

        } else if (listType.equals(SelectionsActivity.DEVICEDIGITAL)){
            String item = (String) wearableListAdapter.getItem(tag);
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEVICE_DIGITAL, item);

        } else if (listType.equals(SelectionsActivity.CONTENT)){
            String item = (String) wearableListAdapter.getItem(tag);
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_CONTENT, item);
        }
    }

    @Override
    public void onTopEmptyRegionClick() {

    }

    @Override
    public void onStart() {
        super.onStart();
        listView.setGreedyTouchMode(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        listView.setGreedyTouchMode(false);
    }
}
