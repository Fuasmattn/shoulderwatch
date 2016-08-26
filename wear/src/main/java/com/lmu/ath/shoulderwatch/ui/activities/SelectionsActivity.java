package com.lmu.ath.shoulderwatch.ui.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.widget.TextView;

import com.lmu.ath.shoulderwatch.R;
import com.lmu.ath.shoulderwatch.ui.adapter.GridPagerAdapter;
import com.lmu.ath.shoulderwatch.ui.fragments.FinishFragment;
import com.lmu.ath.shoulderwatch.ui.fragments.SelectableListFragment;
import com.lmu.ath.shoulderwatch.ui.fragments.PositionFragment;

public class SelectionsActivity extends FragmentActivity implements GridViewPager.OnPageChangeListener {

    public static final String ENVIRONMENT = "ENVIRONMENT";
    public static final String DEVICETYPE = "DEVICETYPE";
    public static final String DEVICEANALOGDIGITAL = "DEVICEANALOG";
    public static final String CONTENT = "CONTENT";
    public static final String SURFTIME = "SURFTIME";
    public static final String SURFRATING = "SURFRATING";
    public static final String SURFDISTANCE = "SURFDISTANCE";
    public static final String RELATIVE_POSITION = "RELATIVE_POSITION";
    public static final String CROWD_LEVEL = "CROWD_LEVEL";
    public static final String DEFENCE_LEVEL = "DEFENCE_LEVEL";


    private  GridPagerAdapter gridPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);

        final DotsPageIndicator mPageIndicator;
        final GridViewPager mViewPager;


        //TODO: add fragments for all listtypes + add content
        final Fragment[] items = {PositionFragment.newInstance(), SelectableListFragment.newInstance(ENVIRONMENT), SelectableListFragment.newInstance(DEVICETYPE), SelectableListFragment.newInstance(DEVICEANALOGDIGITAL)
                , SelectableListFragment.newInstance(CONTENT),SelectableListFragment.newInstance(SURFTIME), SelectableListFragment.newInstance(SURFRATING), SelectableListFragment.newInstance(SURFDISTANCE),
                SelectableListFragment.newInstance(DEFENCE_LEVEL), SelectableListFragment.newInstance(CROWD_LEVEL),
                FinishFragment.newInstance()};

        // Get UI references
        mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (GridViewPager) findViewById(R.id.pager);

        // Assigns an adapter to provide the content for this pager
        gridPagerAdapter = new GridPagerAdapter(getApplicationContext(), getFragmentManager(), items);
        mViewPager.setAdapter(gridPagerAdapter);
        mPageIndicator.setPager(mViewPager);
        mViewPager.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int i, int i1, float v, float v1, int i2, int i3) {

    }

    @Override
    public void onPageSelected(int i, int i1) {
        if (i1 == 3){
            SelectableListFragment deviceFragment = (SelectableListFragment)gridPagerAdapter.getFragment(0, i1);
            deviceFragment.reloadDeviceFragment();

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
