package com.dpsimple;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user_sca on 02.03.2015.
 */
public class Item1Activity extends BaseActivity {

    private static final String TAG = "onPageScrollStateChanged";
    private NotSwipableViewPager viewPager;
    private DynamicFragmentPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View inflate = getLayoutInflater().inflate(R.layout.activity_main, frameLayout);

        viewPager = (NotSwipableViewPager) inflate.findViewById(R.id.pager);

        HashMap<Class<?>, Bundle> classBundleHashMap = new HashMap<>();

        adapter = new DynamicFragmentPagerAdapter(getSupportFragmentManager(),this,classBundleHashMap);
        adapter.setIsTablet(isTablet());
        viewPager.setAdapter(adapter);
        viewPager.setSaveEnabled(false);
        mDrawerList.setItemChecked(position, true);

        setTitle(listArray[position]);
    }


    private boolean isTablet() {
        return getWindow().getDecorView().findViewById(R.id.tabletDefine) != null;
    }

    public void gotoFragmentWithInitialSavedState(Class fragment,  Bundle bundle) {

        getAdapter().addScreen(fragment,bundle,true);
        getAdapter().notifyDataSetChanged();
        viewPager.refreshDrawableState();
    }

    private DynamicFragmentPagerAdapter getAdapter() {
        return ((DynamicFragmentPagerAdapter) viewPager.getAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        gotoFragmentWithInitialSavedState(CarListFragment.class, null);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        adapter.setIsTablet(isTablet());
        viewPager.setCurrentItem(adapter.getCount(), true);
    }

    @Override
    public void onBackPressed() {

        getAdapter().notifyDataSetChanged();
    }
}
