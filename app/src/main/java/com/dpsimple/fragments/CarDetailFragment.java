package com.dpsimple.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dpsimple.CarDetailDescriptionActivity;
import com.dpsimple.R;
import com.dpsimple.fragments.CarScreen.CarDetailDTOScreen;
import com.dpsimple.models.CarModel;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarDetailFragment extends StateFragment<CarDetailDTOScreen> {

    private static final String OBJECT = "object";
    private CarDetailDTOScreen dtoScreen;

    public TextView textView;

    public static CarDetailFragment getInstanse(CarModel item) {
        CarDetailFragment carDetailFragment = new CarDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(OBJECT, item);
        carDetailFragment.setArguments(bundle);
        return carDetailFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.car_detail, null);


        if (getArguments() != null && getArguments().getParcelable(OBJECT) != null) {
            final CarModel carModel = getArguments().getParcelable(OBJECT);
            textView = (TextView) inflate.findViewById(R.id.name);
            textView.setText(carModel.getName());
        }

        return inflate;
    }

    private void startActivity(CarModel carModel) {
        Intent intent = new Intent(getActivity(), CarDetailDescriptionActivity.class);
        intent.putExtra(OBJECT, carModel);

        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getParcelable(OBJECT) != null) {
            CarDetailDTOScreen carDetailDTOScreen = savedInstanceState.getParcelable(OBJECT);

            initFromDTO(carDetailDTOScreen);
        }
    }

    @Override
    public void initFromDTO(CarDetailDTOScreen carDetailDTOScreen) {
//        if (carDetailDTOScreen != null)
//            name.setText(carDetailDTOScreen.name);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView) getView().findViewById(R.id.name);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((CarModel) getArguments().getParcelable(OBJECT));
            }
        });
    }

    @Override
    public CarDetailDTOScreen prepareDto() {
        dtoScreen = new CarDetailDTOScreen();
        //dtoScreen.description = name.getText().toString();

        return dtoScreen;
    }
}
