package com.dpsimple.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDescriptionFragment extends StateFragment<CarDescriptionDTOScreen>{

    private static final String OBJECT = "object";
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        editText = new EditText(getActivity());
        editText.setHint("Description");
        return editText;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getParcelable(OBJECT) != null) {
            CarDescriptionDTOScreen carDescriptionDTOScreen = savedInstanceState.getParcelable(OBJECT);

            initFromDTO(carDescriptionDTOScreen);
        }
    }
    @Override
    protected void initFromDTO(CarDescriptionDTOScreen dto) {
//        editText.setText(dto.description);
    }

    @Override
    protected CarDescriptionDTOScreen prepareDto() {
//       return new CarDescriptionDTOScreen(editText.getText().toString());
        return new CarDescriptionDTOScreen("");
    }
}
