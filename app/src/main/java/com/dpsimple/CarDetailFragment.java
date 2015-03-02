package com.dpsimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dpsimple.fragments.CarScreen.CarDetailDTOScreen;
import com.dpsimple.fragments.StateFragment;
import com.dpsimple.models.CarModel;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarDetailFragment extends Fragment {


    public static final String OBJECT = "object";
    private CarDetailDTOScreen dtoScreen;

    public TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.car_detail, null);

        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null && getArguments().getParcelable(OBJECT) != null) {
            final CarModel carModel = getArguments().getParcelable(OBJECT);
            textView = (TextView) getView().findViewById(R.id.name);
            textView.setText(carModel.getName());
        }
    }

    private void startActivity(CarModel carModel) {
        Intent intent = new Intent(getActivity(), CarDetailDescriptionActivity.class);
        intent.putExtra(OBJECT, carModel);

        startActivity(intent);
    }




}
