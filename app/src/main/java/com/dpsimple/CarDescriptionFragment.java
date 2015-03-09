package com.dpsimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDescriptionFragment extends KillableFragment {

    public static final String OBJECT = "object";

    private EditText editText;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            return inflater.inflate(R.layout.description, null);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        view = getView();
    }


}
