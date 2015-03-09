package com.dpsimple;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dpsimple.models.CarModel;

import java.lang.reflect.Field;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarDetailFragment extends KillableFragment {


    public static final String OBJECT = "object";
    public TextView textView;
    private Button btEdit;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null)
            return inflater.inflate(R.layout.car_detail, null);
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();

        view = getView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null && getArguments().getParcelable(OBJECT) != null) {
            final CarModel carModel = getArguments().getParcelable(OBJECT);
            textView = (TextView) getView().findViewById(R.id.name);
            textView.setText(carModel.getName());


            btEdit = (Button) getView().findViewById(R.id.btEdit);
            btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(CarDescriptionFragment.OBJECT, carModel);
                    ((Item1Activity) getActivity()).gotoFragmentWithInitialSavedState(CarDescriptionFragment.class, bundle);

                }
            });
        }
    }


}
