package com.dpsimple;

import android.os.Bundle;
import android.os.Parcelable;

import com.dpsimple.fragments.CarDescriptionFragment;
import com.dpsimple.fragments.CarDetailFragment;
import com.dpsimple.fragments.DualPaneActivity;
import com.dpsimple.models.CarModel;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDetailDescriptionActivity extends DualPaneActivity<CarDetailFragment,CarDescriptionFragment>{

    public static final String OBJECT = "object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CarModel carModel = getIntent().getParcelableExtra(OBJECT);
        setLeftFragment(CarDetailFragment.getInstanse(carModel));

        setRightFragment(new CarDescriptionFragment());
    }
}
