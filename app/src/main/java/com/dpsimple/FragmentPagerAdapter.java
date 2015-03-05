/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dpsimple;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Implementation of {@link android.support.v4.view.PagerAdapter} that
 * represents each page as a {@link Fragment} that is persistently
 * kept in the fragment manager as long as the user can return to the page.
 * <p/>
 * <p>This version of the pager is best for use when there are a handful of
 * typically more static fragments to be paged through, such as a set of tabs.
 * The fragment of each page the user visits will be kept in memory, though its
 * view hierarchy may be destroyed when not visible.  This can result in using
 * a significant amount of memory since fragment instances can hold on to an
 * arbitrary amount of state.  For larger sets of pages, consider
 * {@link FragmentStatePagerAdapter}.
 * <p/>
 * <p>When using FragmentPagerAdapter the host ViewPager must have a
 * valid ID set.</p>
 * <p/>
 * <p>Subclasses only need to implement {@link #getItem(int)}
 * and {@link #getCount()} to have a working adapter.
 * <p/>
 * <p>Here is an example implementation of a pager containing fragments of
 * lists:
 * <p/>
 * {@sample development/samples/Support4Demos/src/com/example/android/supportv4/app/FragmentPagerSupport.java
 * complete}
 * <p/>
 * <p>The <code>R.layout.fragment_pager</code> resource of the top-level fragment is:
 * <p/>
 * {@sample development/samples/Support4Demos/res/layout/fragment_pager.xml
 * complete}
 * <p/>
 * <p>The <code>R.layout.fragment_pager_list</code> resource containing each
 * individual fragment's layout is:
 * <p/>
 * {@sample development/samples/Support4Demos/res/layout/fragment_pager_list.xml
 * complete}
 */
public abstract class FragmentPagerAdapter extends PagerAdapter {
    private static final String TAG = "FragmentPagerAdapter";
    private static final boolean DEBUG = false;

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    public FragmentPagerAdapter(FragmentManager fm) {
        mFragmentManager = fm;
    }

    /**
     * Return the Fragment associated with a specified position.
     */
    public abstract Fragment getItem(int position);

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        if (mCurTransaction == null) {
//            mCurTransaction = mFragmentManager.beginTransaction();
//        }

        // Do we already have this fragment?
        String name = makeFragmentName(position);
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            mFragmentManager.beginTransaction()
                    .detach(fragment)
                  //  .remove(fragment)
                    .commit();
            mFragmentManager.beginTransaction()
                    .add(container.getId(), fragment)
                    .commit();

            mFragmentManager.executePendingTransactions();
        }

//        if (fragment != null) {
//            if (DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragment);
//            mCurTransaction.add(container.getId(),fragment);
//        }
//        else {
//            fragment = getItem(position);
//            if (DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragment);
//            mCurTransaction.add(container.getId(), fragment,
//                    makeFragmentName(position));
//        }
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (DEBUG) Log.v(TAG, "Detaching item #" + position + ": f=" + object
                + " v=" + ((Fragment) object).getView());
        mCurTransaction.detach((Fragment) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        mFragmentManager.executePendingTransactions();
//        if (mCurTransaction != null) {
//            mCurTransaction.commitAllowingStateLoss();
//            mCurTransaction = null;
//            mFragmentManager.executePendingTransactions();
//        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    private static String makeFragmentName(int index) {
        return "" + index;

    }

    public Fragment getFragmentByPosition(int position) {
        return mFragmentManager.findFragmentByTag("" + position);
    }

    public void putFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .add(fragment, makeFragmentName(getCount() - 1))
                .commit();

        mFragmentManager.executePendingTransactions();
    }

    public void removeFragment(int position) {

        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(makeFragmentName(position));
        if (fragmentByTag == null) return;
        mFragmentManager.beginTransaction()
                .remove(fragmentByTag).commit();

        mFragmentManager.executePendingTransactions();

    }

    public int getPositionOfFragment(Fragment fragment) {
        return mFragmentManager.getFragments().indexOf(fragment);
    }

    public int getPositionOfFragment(Class clazz) {

        if (mFragmentManager.getFragments() == null) return -1;
        for (Fragment fragment : mFragmentManager.getFragments()) {
            if (fragment.getClass() == clazz)
                return mFragmentManager.getFragments().indexOf(fragment);
        }

        return -1;
    }
}
