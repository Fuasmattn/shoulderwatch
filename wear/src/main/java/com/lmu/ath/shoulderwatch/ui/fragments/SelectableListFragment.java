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
import android.widget.TextView;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.database.DataManager;
import com.lmu.ath.shoulderwatch.database.ShoulderWatchTable;
import com.lmu.ath.shoulderwatch.ui.activities.SelectionsActivity;
import com.lmu.ath.shoulderwatch.ui.adapter.WearableListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectableListFragment extends Fragment implements WearableListView.OnCentralPositionChangedListener, WearableListView.OnScrollListener{


    private WearableListAdapter wearableListAdapter = null;
    private String listType;
    private DataManager dataManager;
    private WearableListView listView;
    private TextView titleText;


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
        View view = inflater.inflate(R.layout.fragment_selectable_list, container, false);

       // WearableListView listView = (WearableListView) view.findViewById(R.id.wearable_list);
        listView = (WearableListView) view.findViewById(R.id.wearable_list);
       // listView.addOnScrollListener(this);
        listView.addOnCentralPositionChangedListener(this);
        listType = getArguments().getString("type");

        dataManager = DataManager.getInstance();
        titleText = (TextView)view.findViewById(R.id.title);

        // Icon Initialization
        ArrayList<Drawable> environmentIcons = new ArrayList<Drawable>();
        environmentIcons.add(getResources().getDrawable(R.mipmap.metro, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.place, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.building, null));
        environmentIcons.add(getResources().getDrawable(R.mipmap.event, null));

        ArrayList<Drawable> typeIcons = new ArrayList<Drawable>();
        typeIcons.add(getResources().getDrawable(R.mipmap.analog, null));
        typeIcons.add(getResources().getDrawable(R.mipmap.digital, null));

        ArrayList<Drawable> analogDeviceIcons = new ArrayList<Drawable>();
        analogDeviceIcons.add(getResources().getDrawable(R.mipmap.buch, null));
        analogDeviceIcons.add(getResources().getDrawable(R.mipmap.zeitung, null));
        analogDeviceIcons.add(getResources().getDrawable(R.mipmap.notiz, null));

        ArrayList<Drawable> digitalDeviceIcons = new ArrayList<Drawable>();
        digitalDeviceIcons.add(getResources().getDrawable(R.mipmap.phone, null));
        digitalDeviceIcons.add(getResources().getDrawable(R.mipmap.tablet, null));
        digitalDeviceIcons.add(getResources().getDrawable(R.mipmap.laptop, null));
        digitalDeviceIcons.add(getResources().getDrawable(R.mipmap.watch, null));


        ArrayList<Drawable> contentIcons = new ArrayList<Drawable>();
        contentIcons.add(getResources().getDrawable(R.mipmap.text, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.bild, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.nachricht, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.passwort, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.spiele, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.video, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.banking, null));
        contentIcons.add(getResources().getDrawable(R.mipmap.maps, null));

        ArrayList<Drawable> surfRatingIcons = new ArrayList<Drawable>();
        surfRatingIcons.add(getResources().getDrawable(R.mipmap.easy, null));
        surfRatingIcons.add(getResources().getDrawable(R.mipmap.medium, null));
        surfRatingIcons.add(getResources().getDrawable(R.mipmap.hard, null));

        ArrayList<Drawable> defenceLevelIcons = new ArrayList<Drawable>();
        defenceLevelIcons.add(getResources().getDrawable(R.mipmap.unsafe, null));
        defenceLevelIcons.add(getResources().getDrawable(R.mipmap.mediumsafe, null));
        defenceLevelIcons.add(getResources().getDrawable(R.mipmap.totallysafe, null));

        ArrayList<Drawable> crowdDensityIcons = new ArrayList<Drawable>();
        crowdDensityIcons.add(getResources().getDrawable(R.mipmap.wenige, null));
        crowdDensityIcons.add(getResources().getDrawable(R.mipmap.einige, null));
        crowdDensityIcons.add(getResources().getDrawable(R.mipmap.viele, null));
        crowdDensityIcons.add(getResources().getDrawable(R.mipmap.sehrviele, null));


        Context context = getActivity();


        if (listType.equals(SelectionsActivity.ENVIRONMENT)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.environment), environmentIcons);
            titleText.setText("Umgebung");
        } else if (listType.equals(SelectionsActivity.DEVICETYPE)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.devicetype), typeIcons);
            titleText.setText("Gerätetyp");
        } else if (listType.equals(SelectionsActivity.DEVICEANALOG)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.device_analog), analogDeviceIcons);
            titleText.setText("Geräte - analog");
        } else if (listType.equals(SelectionsActivity.DEVICEDIGITAL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.device_digital), digitalDeviceIcons);
            titleText.setText("Geräte - digital");
        } else if (listType.equals(SelectionsActivity.CONTENT)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.content), contentIcons);
            titleText.setText("Inhalt");
        } else if (listType.equals(SelectionsActivity.SURFRATING)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.surf_rating), surfRatingIcons);
            titleText.setText("Inhalte zu sehen");
        }else if (listType.equals(SelectionsActivity.RELATIVE_POSITION)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.relative_position), environmentIcons);
            titleText.setText("Position");
        }else if (listType.equals(SelectionsActivity.CROWD_LEVEL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.crowd_density), crowdDensityIcons);
            titleText.setText("Anzahl umgebende Personen");
        }else if (listType.equals(SelectionsActivity.DEFENCE_LEVEL)){
            wearableListAdapter = new WearableListAdapter(context, getResources().getStringArray(R.array.defence_level), defenceLevelIcons);
            titleText.setText("Verdeckung");
        }

        if (wearableListAdapter != null){
            listView.setAdapter(wearableListAdapter);
        }

        //Save initial values to DB
        saveValues(0);

        return view;
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

    @Override
    public void onScroll(int i) {

    }

    @Override
    public void onAbsoluteScrollChange(int i) {

    }

    @Override
    public void onScrollStateChanged(int i) {

    }

    @Override
    public void onCentralPositionChanged(int i) {
        saveValues(i);
    }

    public void saveValues (int i){
        String item = (String) wearableListAdapter.getItem(i);

        if (listType.equals(SelectionsActivity.ENVIRONMENT)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_ENVIRONMENT, item);

        } else if (listType.equals(SelectionsActivity.DEVICETYPE)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEVICETYPE, item);

        } else if (listType.equals(SelectionsActivity.DEVICEANALOG)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEVICE_ANALOG, item);

        } else if (listType.equals(SelectionsActivity.DEVICEDIGITAL)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEVICE_DIGITAL, item);

        } else if (listType.equals(SelectionsActivity.CONTENT)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_CONTENT, item);

        } else if (listType.equals(SelectionsActivity.SURFRATING)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_SURF_RATING, item);

        } else if (listType.equals(SelectionsActivity.CROWD_LEVEL)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_CROWD_DENSITY, item);

        } else if (listType.equals(SelectionsActivity.DEFENCE_LEVEL)){
            dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_DEFENCE_LEVEL, item);
        }
    }
}
