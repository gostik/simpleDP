package com.dpsimple.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 * Created by user_sca on 26.02.2015.
 */
public abstract class StateFragment<T extends Parcelable> extends Fragment {

    private static final String OBJECT = "object";

//    public static StateFragment<? extends Object> newInstance(Parcelable parcelable) {
//        StateFragment<? extends Object> f = new StateFragment<Object>();
//        Bundle bundle = new Bundle();
//
//        bundle.putParcelable(OBJECT,parcelable);
//        f.setArguments(bundle);
//        return f;
//    }
    T dto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState!=null && savedInstanceState.getParcelable(OBJECT)!=null) {
            dto = (T) savedInstanceState.getParcelable(OBJECT);

            initFromDTO(dto);
        }
    }

    protected abstract void initFromDTO(T dto);

    @Override
    public void onPause() {
        super.onPause();

        dto = prepareDto();
    }

    protected abstract T prepareDto();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(OBJECT, dto);
        super.onSaveInstanceState(outState);
    }

//    public T getObject(){
//        try {
//            return (T) getArguments().getParcelable(OBJECT);
//        }catch (ClassCastException ex){
//            ex.printStackTrace();
//        }
//       return null;
//    }
//
//    @Override
//    public T load() {
//        return getObject();
//    }


}
