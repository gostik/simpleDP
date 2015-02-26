package com.dpsimple.fragments;

import java.util.List;

/**
 * Created by user_sca on 26.02.2015.
 */
public class DetailListStateFragment<T> extends StateFragment<T> {

    @Override
    public void onResume() {
        super.onResume();
        T load = load();
    }
}
