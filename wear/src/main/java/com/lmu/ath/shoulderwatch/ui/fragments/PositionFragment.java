package com.lmu.ath.shoulderwatch.ui.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.database.DataManager;
import com.lmu.ath.shoulderwatch.database.ShoulderWatchTable;
import com.lmu.ath.shoulderwatch.ui.activities.SelectionsActivity;
import com.lmu.ath.shoulderwatch.ui.util.DrawView;

public class PositionFragment extends Fragment implements View.OnClickListener, PositionFragmentTouchListener {

    private View root;
    private DrawView drawView;
    private DataManager dataManager;

    public static PositionFragment newInstance() {
        PositionFragment fragment = new PositionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataManager = DataManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_position, container, false);
        drawView = (DrawView) root.findViewById(R.id.drawView);
        drawView.setTouchListener(this);
        ((Button) root.findViewById(R.id.buttonDrehen)).setOnClickListener(this);

        int currentPosition = drawView.getCurrentSelectedPosition();
        dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_RELATIVE_POSITION, String.valueOf(currentPosition));

        return root;
    }


    @Override
    public void onClick(View v) {
        drawView.turnIcon();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void positionSelected(int position) {
        dataManager.addStringValueToDatabaseRecord(ShoulderWatchTable.COLUMN_RELATIVE_POSITION, String.valueOf(position));
    }
}
