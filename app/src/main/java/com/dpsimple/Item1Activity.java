package com.dpsimple;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by user_sca on 02.03.2015.
 */
public class Item1Activity extends BaseActivity {

    private static final String TAG = "onPageScrollStateChanged";
    private NotSwipableViewPager viewPager;
  //  private LinkedListFragmentPagerAdapter adapter;
//
//    private ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
//
//        @Override
//        public void onPageSelected(int arg0) {}
//
//        @Override
//        public void onPageScrolled(int arg0, float arg1, int arg2) {}
//
//        @Override
//        public void onPageScrollStateChanged(int arg0) {
//            if (arg0 == ViewPager.SCROLL_STATE_IDLE) {
//                //if(getSupportFragmentManager().getFragments().size()<mAdapter.mFragments.size()) {
//                updateAdapter();
//                Log.d(TAG, "--------------------------");
//                Log.d(TAG,"Adjust fragments number");
//                Log.d(TAG,"---------------------------");
//                //}
//
//            }
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View inflate = getLayoutInflater().inflate(R.layout.activity_main, frameLayout);

        viewPager = (NotSwipableViewPager) inflate.findViewById(R.id.pager);
//        adapter = new LinkedListFragmentPagerAdapter(getSupportFragmentManager());
//        adapter.setIsTablet(isTablet());
//        viewPager.setAdapter(adapter);
      //  viewPager.setOnPageChangeListener(mListener);
        viewPager.setSaveEnabled(false);
        mDrawerList.setItemChecked(position, true);

        setTitle(listArray[position]);
    }


    private boolean isTablet() {
        return getWindow().getDecorView().findViewById(R.id.tabletDefine) != null;
    }

    public void gotoFragmentWithInitialSavedState(Fragment fragment, Fragment.SavedState savedState) {
//        if (savedState != null)
//            fragment.setInitialSavedState(savedState);
//        getAdapter().addFragment(fragment);
//        getAdapter().notifyDataSetChanged();
//        updateAdapter();
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        trans.replace(R.id.root_frame, fragment);
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);

        trans.commit();
    }

    private LinkedListFragmentPagerAdapter getAdapter() {
        return ((LinkedListFragmentPagerAdapter) viewPager.getAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        gotoFragmentWithInitialSavedState(new CarListFragment(), null);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
   //     viewPager.setAdapter(new LinkedListFragmentPagerAdapter(getSupportFragmentManager()));
    //    adapter.setIsTablet(isTablet());
        updateAdapter();
      //  viewPager.setCurrentItem(adapter.getCount(), true);
    }

    private void updateAdapter() {
//       s
    }

    @Override
    public void onBackPressed() {

        getAdapter().removeLastFragment();
        getAdapter().notifyDataSetChanged();
    }
}
