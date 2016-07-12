package com.lmu.ath.shoulderwatch.ui.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.ui.adapter.GridPagerAdapter;
import com.lmu.ath.shoulderwatch.ui.fragments.SelectableListFragment;

public class SelectionsActivity extends FragmentActivity {

    public static final String ENVIRONMENT = "ENVIRONMENT";
    public static final String DEVICETYPE = "DEVICETYPE";
    public static final String DEVICEANALOG = "DEVICEANALOG";
    public static final String DEVICEDIGITAL = "DEVICEDIGITAL";
    public static final String CONTENT = "CONTENT";
    public static final String SURFRATING = "SURFRATING";
    public static final String RELATIVE_POSITION = "RELATIVE_POSITION";
    public static final String CROWD_LEVEL = "CROWD_LEVEL";
    public static final String DEFENCE_LEVEL = "DEFENCE_LEVEL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);

        final DotsPageIndicator mPageIndicator;
        final GridViewPager mViewPager;


        //TODO: add fragments for all listtypes + add content
        final Fragment[] items = {SelectableListFragment.newInstance(ENVIRONMENT), SelectableListFragment.newInstance(DEVICETYPE), SelectableListFragment.newInstance(DEVICEANALOG)
                , SelectableListFragment.newInstance(DEVICEDIGITAL), SelectableListFragment.newInstance(CONTENT)};

        // Get UI references
        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        // Assigns an adapter to provide the content for this pager
        mViewPager.setAdapter(new GridPagerAdapter(getApplicationContext(), getFragmentManager(), items));
        mPageIndicator.setPager(mViewPager);
    }



}
