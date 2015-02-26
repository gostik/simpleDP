package com.dpsimple;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import com.dpsimple.models.CarModel;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDetailActivity extends BaseActivity{


    public static final String OBJECT = "object";
    private CarModel carModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);

        setContentView(textView);
        Parcelable parcelableExtra = getIntent().getParcelableExtra(OBJECT);

        if (parcelableExtra!=null) {
            carModel = (CarModel) parcelableExtra;
            textView.setText(carModel.getName());
        }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(carModel);
            }
        });
    }

    private void startActivity(CarModel carModel) {
        Intent intent = new Intent(this,CarDetailDescriptionActivity.class);
        intent.putExtra(OBJECT, carModel);

        startActivity(intent);
    }
}
