package com.dpsimple;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NotSwipableViewPager extends ViewPager {
    private boolean mIsEnabledSwipe = false;

    public NotSwipableViewPager(Context context) {
        super(context);
    }

    public NotSwipableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!mIsEnabledSwipe) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void setEnabledSwipe(boolean enabled) {
        mIsEnabledSwipe = enabled;
    }

}