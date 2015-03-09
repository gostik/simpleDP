package com.dpsimple;

import android.support.v4.app.Fragment;

/**
 * Created by user_sca on 10.03.2015.
 */
public class KillableFragment extends Fragment implements Killable {
    @Override
    public void kill() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
