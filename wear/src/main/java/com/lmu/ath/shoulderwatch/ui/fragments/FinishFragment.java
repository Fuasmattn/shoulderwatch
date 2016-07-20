package com.lmu.ath.shoulderwatch.ui.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.database.DataManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinishFragment extends Fragment {


    private DataManager dataManager;
    private Button finish;


    public static FinishFragment newInstance() {
        FinishFragment finishFragment = new FinishFragment();
        return finishFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finish, container, false);
        dataManager = DataManager.getInstance();

        try {
            finish = (Button) view.findViewById(R.id.finish_button);
            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dataManager.saveDataBaseRecord(getActivity());
                    Activity parentactivity = getActivity();
                    parentactivity.finish();
                }
            });
        } catch (ClassCastException e){
            e.printStackTrace();
        }


        return view;
    }

}
