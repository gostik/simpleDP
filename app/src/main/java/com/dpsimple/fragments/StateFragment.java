package com.dpsimple.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 * Created by user_sca on 26.02.2015.
 */
public class StateFragment<T> extends Fragment implements IState<T> {

    private static final String OBJECT = "object";

    public static StateFragment newInstance(Parcelable parcelable) {
        StateFragment f = new StateFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable(OBJECT,parcelable);
        f.setArguments(bundle);
        return f;
    }

    public T getParcelable(){
        try {
            return (T) getArguments().getParcelable(OBJECT);
        }catch (ClassCastException ex){
            ex.printStackTrace();
        }
       return null;
    }

    @Override
    public T load() {
        return getParcelable();
    }

}
