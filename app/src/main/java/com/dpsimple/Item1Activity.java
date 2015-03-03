package com.dpsimple;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashMap;
import java.util.List;

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

    }

    private boolean isTablet() {
        return getWindow().getDecorView().findViewById(R.id.tabletDefine) != null;
    }

    public void gotoFragmentWithInitialSavedState(Class fragment, Bundle bundle) {

        getAdapter().addScreen(fragment, bundle, true);

        getAdapter().notifyDataSetChanged();

        viewPager.setCurrentItem(getAdapter().getCount());
        viewPager.refreshDrawableState();
    }

    private DynamicFragmentPagerAdapter getAdapter() {
        return ((DynamicFragmentPagerAdapter) viewPager.getAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();

        View inflate = getLayoutInflater().inflate(R.layout.activity_main, frameLayout);

        viewPager = (NotSwipableViewPager) inflate.findViewById(R.id.pager);

        viewPager.setSaveEnabled(true);

        mDrawerList.setItemChecked(position, true);

        if (adapter != null) {
            List<Fragment> enabledScreens = adapter.getEnabledScreens();
            viewPager.setAdapter(null);
            viewPager.setAdapter(new DynamicFragmentPagerAdapter(getSupportFragmentManager(), this, enabledScreens));
           // viewPager.setCurrentItem(getAdapter().getCount() - 1);
            viewPager.refreshDrawableState();
        }

        if (getAdapter() == null || getAdapter().getCount() == 0) {
            viewPager = (NotSwipableViewPager) inflate.findViewById(R.id.pager);

            viewPager.setAdapter(new DynamicFragmentPagerAdapter(getSupportFragmentManager(), this, new HashMap<Class<?>, Bundle>()));

            gotoFragmentWithInitialSavedState(CarListFragment.class, null);

            getAdapter().setIsTablet(isTablet());

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        getAdapter().setIsTablet(isTablet());
        viewPager.setCurrentItem(getAdapter().getLastEnabledIndex(), true);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        adapter = (DynamicFragmentPagerAdapter) getLastCustomNonConfigurationInstance();


    }

    @Override
    public void onBackPressed() {

        Integer lastEnabledIndex = getAdapter().getLastEnabledIndex();
        if (lastEnabledIndex != null) {
            getAdapter().setEnabled(lastEnabledIndex, false);

        }
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return getAdapter();
    }
}
