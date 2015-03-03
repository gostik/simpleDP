package com.dpsimple;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by user_sca on 02.03.2015.
 */
public class DynamicFragmentPagerAdapter extends FragmentPagerAdapter implements IsTabletListener {

    public final LinkedList<Fragment> screens = new LinkedList<Fragment>();

    private Context context;
    private List<AtomicBoolean> flags = new ArrayList<AtomicBoolean>();
    private boolean isTablet;

//    public DynamicFragmentPagerAdapter(FragmentManager fm, Context context, List<Class<?>> screens) {
//        super(fm);
//        this.context = context;
//
//        for (Class<?> screen : screens)
//            addScreen(screen, null);
//
//        notifyDataSetChanged();
//    }

    public DynamicFragmentPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        if (fragments != null)
            for (Fragment fragment : fragments) {
                addScreen(fragment);
            }
    }

    public DynamicFragmentPagerAdapter(FragmentManager fm, Context context, Map<Class<?>, Bundle> screens) {
        super(fm);
        this.context = context;

        for (Class<?> screen : screens.keySet())
            addScreen(screen, screens.get(screen));

        notifyDataSetChanged();
    }


    public void addScreen(Class<?> clazz, Bundle args, boolean rewriteIfLastIsSame) {
        if (rewriteIfLastIsSame) {

            for (Fragment screen : screens) {
                if (screen.getClass() == clazz && isEnabled(screens.indexOf(screen)))
                    setEnabled(screens.indexOf(screen), false);

            }
        }

        addScreen(clazz, args);
    }

    public void addScreen(Class<?> clazz, Bundle args) {
        screens.add(Fragment.instantiate(context, clazz.getName(), args));
        flags.add(new AtomicBoolean(true));
    }

    public void addScreen(Fragment fragment) {
        screens.add(fragment);
        flags.add(new AtomicBoolean(true));
    }

    public Integer getLastEnabledIndex() {
        Iterator<Fragment> fragmentIterator = screens.descendingIterator();
        while (fragmentIterator.hasNext()) {
            Fragment next = fragmentIterator.next();
            if (isEnabled(screens.indexOf(next)))
                return screens.indexOf(next);

        }

        return null;
    }

    public boolean isEnabled(int position) {
        return flags.get(position).get();
    }

    public void setEnabled(int position, boolean enabled) {
        AtomicBoolean flag = flags.get(position);
        if (flag.get() != enabled) {
            flag.set(enabled);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        int n = 0;
        for (AtomicBoolean flag : flags) {
            if (flag.get())
                n++;
        }
        return n;
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
    public Fragment getItem(int position) {
        return screens.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE; // To make notifyDataSetChanged() do something
    }

    @Override
    public void notifyDataSetChanged() {
        try {
            super.notifyDataSetChanged();
        } catch (Exception e) {
            Log.w("error when updating", e);
        }
    }

    public List<Fragment> getEnabledScreens() {
        List<Fragment> res = new ArrayList<Fragment>();
        for (int n = 0; n < screens.size(); n++) {
            if (isEnabled(n))
                res.add(screens.get(n));
        }
        return res;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // We map the requested position to the position as per our screens array list
        Fragment fragment = getEnabledScreens().get(position);
        int internalPosition = screens.indexOf(fragment);
        return super.instantiateItem(container, internalPosition);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Fragment fragment = getEnabledScreens().get(position);
        if (fragment instanceof TitledFragment)
            return ((TitledFragment) fragment).getTitle(context);
        return super.getPageTitle(position);
    }

    @Override
    public void setIsTablet(boolean isTablet) {
        this.isTablet = isTablet;
    }

    @Override
    public boolean getIsTablet() {
        return isTablet;
    }

    public void invalidate(Class clazz) {
        for (Fragment screen : screens) {
            if (screen.getClass() == clazz)
                setEnabled(screens.indexOf(screen), false);
        }
    }

    public static interface TitledFragment {
        public String getTitle(Context context);
    }
}
