package com.lmu.ath.shoulderwatch.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;

/**
 * Created by daniela on 11.07.16.
 */

public class GridPagerAdapter extends FragmentGridPagerAdapter{

    private final Context context;
    private final Fragment[] fragments;

    public GridPagerAdapter(Context context, FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }


    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int row) {
        return fragments.length;
    }

    @Override
    public Fragment getFragment(int row, int column) {
        return this.fragments[column];
    }
}
