package com.dpsimple.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 * Created by user_sca on 26.02.2015.
 */
public abstract class StateFragment<T extends Parcelable> extends Fragment {

    private static final String OBJECT = "object";

    T dto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getParcelable(OBJECT) != null) {
            dto = (T) savedInstanceState.getParcelable(OBJECT);

            initFromDTO(dto);
        }
    }

    protected abstract void initFromDTO(T dto);

    protected abstract T prepareDto();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(OBJECT, prepareDto());
        super.onSaveInstanceState(outState);
    }
}
