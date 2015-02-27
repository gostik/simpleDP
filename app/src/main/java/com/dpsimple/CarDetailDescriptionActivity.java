package com.dpsimple;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.dpsimple.fragments.CarDescriptionActivity;
import com.dpsimple.fragments.CarDescriptionFragment;
import com.dpsimple.fragments.CarDetailFragment;
import com.dpsimple.fragments.DualPaneActivity;
import com.dpsimple.models.CarModel;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDetailDescriptionActivity extends DualPaneActivity<CarDetailFragment, CarDescriptionFragment> {

    public static final String OBJECT = "object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CarModel carModel= getIntent().getParcelableExtra(OBJECT);
        setLeftFragment(CarDetailFragment.getInstanse(carModel));
    }

    @Override
    protected void onResume() {
        super.onResume();



        getLeftFragment().textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isTablet()) {
                    startActivity(new Intent(CarDetailDescriptionActivity.this, CarDescriptionActivity.class));
                } else
                    setRightFragment(new CarDescriptionFragment());
            }
        });

    }
}
