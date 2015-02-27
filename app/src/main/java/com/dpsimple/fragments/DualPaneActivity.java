package com.dpsimple.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.dpsimple.BaseActivity;
import com.dpsimple.R;

/**
 * Created by user_sca on 26.02.2015.
 */
public class DualPaneActivity<LEFT extends Fragment, RIGHT extends Fragment> extends BaseActivity {

    private FrameLayout leftFrame;
    private FrameLayout rightFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.fragment_layout, frameLayout);

        leftFrame = (FrameLayout) findViewById(R.id.left_frame);
        rightFrame = (FrameLayout) findViewById(R.id.right_frame);
        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle(listArray[position]);
    }

    public boolean isTablet() {
        return findViewById(R.id.right_frame) != null;
    }

    public void setLeftFragment(LEFT fragment) {
        setFragment(leftFrame.getId(), fragment, "left");
    }

    public void setRightFragment(RIGHT fragment) {
        setFragment(rightFrame.getId(), fragment, "right");
    }

    public void setFragment(int res_id, Fragment fragment, String tag) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(res_id, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public LEFT getLeftFragment() {
        return (LEFT) getSupportFragmentManager().findFragmentByTag("left");
    }

    public RIGHT getRIGHTFragment() {
        if (!isTablet()) return null;
        return (RIGHT) getSupportFragmentManager().findFragmentByTag("right");
    }


}
