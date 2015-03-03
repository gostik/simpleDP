package com.dpsimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDescriptionFragment extends Fragment{

    public static final String OBJECT = "object";

    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        editText = new EditText(getActivity());
        editText.setHint("Description");
        return editText;
    }


}
