package com.dpsimple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by user_sca on 12.03.2015.
 */
public class DynamicFragmentPagerAdapter2 extends FragmentPagerAdapter implements IsTabletListener {
    private FragmentManager fm;
    private boolean isTablet;

    public DynamicFragmentPagerAdapter2(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fm.findFragmentByTag(makeFragmentName(position));
    }

    @Override
    public int getCount() {
        if (fm.getFragments()==null) return 0;
        return fm.getFragments().size();
    }

    public void addFragment(Fragment fragment) {
        fm.beginTransaction().add(fragment, makeFragmentName(getCount())).commit();

        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        try {
            super.notifyDataSetChanged();
        } catch (Exception e) {
            Log.w("error when updating", e);
        }
    }
    public void hideFragment(Fragment fragment) {
        fm.beginTransaction().hide(fragment).commit();

        notifyDataSetChanged();
    }

    public void hideFragmentByPos(int pos) {
        Fragment item = getItem(pos);
        if (item != null)
            hideFragment(item);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (getItem(position) != null) return getItem(position);

        return super.instantiateItem(container, position);
    }

    @Override
    public float getPageWidth(int position) {
        if (isTablet) {
            return (0.5f);
        } else {
            return (1.0f);
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE; // To make notifyDataSetChanged() do something
    }

    @Override
    public void setIsTablet(boolean isTablet) {
        this.isTablet = isTablet;
    }

    @Override
    public boolean getIsTablet() {
        return isTablet;
    }
}
