package com.dpsimple.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.dpsimple.BaseActivity;
import com.dpsimple.R;

/**
 * Created by user_sca on 26.02.2015.
 */
public class DualPaneActivity extends BaseActivity{

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

    public boolean isTablet(){
        return rightFrame!=null;
    }
    public void setLeftFragment(StateFragment stateFragment){
        setFragment(leftFrame.getId(),stateFragment);
    }

    public void setRightFragment(StateFragment stateFragment){
        setFragment(rightFrame.getId(),stateFragment);
    }

    public void setFragment(int res_id, Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(res_id, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
