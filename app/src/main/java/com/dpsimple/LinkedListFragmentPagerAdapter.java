package com.dpsimple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by user_sca on 02.03.2015.
 */
public class LinkedListFragmentPagerAdapter extends FragmentPagerAdapter implements IsTabletListener {
    public LinkedList<Fragment> mFragments = new LinkedList<Fragment>(
           );// Arrays.asList(new RootFragment())
    private boolean isTablet;

    public LinkedListFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
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
    public int getCount() {
        return mFragments.size();
    }

    public void addFragment(Fragment fragment) {
//        try {
//            if (mFragments.getLast() != null &&
//                    mFragments.getLast().getClass() == fragment.getClass()) {
//                mFragments.removeLast();
//                notifyDataSetChanged();
//
//            }
//        } catch (NoSuchElementException ex) {
//            //just continue
//        }


        //mFragments.addLast(fragment);
        //mFragments.addLast(new RootFragment());

        notifyDataSetChanged();
    }

    public void removeLastFragment() {
        mFragments.removeLast();
        notifyDataSetChanged();
    }


    @Override
    public void setIsTablet(boolean isTablet) {

        this.isTablet = isTablet;
    }

    @Override
    public boolean getIsTablet() {
        return isTablet;
    }

    @Override
    public int getItemPosition(Object object) {
        //  if ((getCount()==1 && getIsTablet()) || (getCount()==0 && !getIsTablet())) {
        return PagerAdapter.POSITION_NONE;
        //}
        // return PagerAdapter.POSITION_UNCHANGED;
    }
}
