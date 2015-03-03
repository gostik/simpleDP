package com.dpsimple;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

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

        if (getLastCustomNonConfigurationInstance() == null)
            adapter = new DynamicFragmentPagerAdapter(
                    getSupportFragmentManager(),
                    this,
                    new ArrayList<Fragment>());
        else adapter = (DynamicFragmentPagerAdapter) getLastCustomNonConfigurationInstance();
        adapter.setIsTablet(isTablet());
        viewPager.setAdapter(adapter);
        int i = viewPager.getAdapter().getCount() - 1;

        viewPager.setCurrentItem(i);
        viewPager.setSaveEnabled(false);
        mDrawerList.setItemChecked(position, true);

        setTitle(listArray[position]);
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
        gotoFragmentWithInitialSavedState(CarListFragment.class, null);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        adapter.setIsTablet(isTablet());
        viewPager.setCurrentItem(adapter.getLastEnabledIndex(), true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
////
//        List<Fragment> fragmentsInManager = getSupportFragmentManager().getFragments();

//        for (Fragment fragment : adapter.getEnabledScreens()) {
//
//            getSupportFragmentManager().putFragment(outState, fragment.getClass().toString(), fragment);
//        }


        super.onSaveInstanceState(outState);
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
        return adapter;
    }
}
