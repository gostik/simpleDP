package com.dpsimple.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dpsimple.R;
import com.dpsimple.fragments.CarScreen.CarListDTOScreen;
import com.dpsimple.models.CarModel;
import com.dpsimple.models.Repo;

import java.util.List;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarListFragment extends StateFragment<CarListDTOScreen> {


    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.month_list, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) view.findViewById(R.id.month_list);
        listView.setAdapter(new MonthAdapter(getActivity(), 0, Repo.detailModelsRepoMonth));
    }

    @Override
    protected void initFromDTO(CarListDTOScreen dto) {
        if (dto.choosenCar != null) {
            int i = Repo.detailModelsRepoMonth.indexOf(dto.choosenCar);
            listView.setSelection(i);
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        listView.setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected CarListDTOScreen prepareDto() {
        CarModel selectedItem = (CarModel) listView.getSelectedItem();
        CarListDTOScreen carListDTOScreen = new CarListDTOScreen();
        carListDTOScreen.choosenCar = selectedItem;
        return carListDTOScreen;
    }

    private class MonthAdapter extends ArrayAdapter<CarModel> {
        public MonthAdapter(Context context, int resource, List<CarModel> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView = new TextView(getContext());
            textView.setText(getItem(position).getName());
            return textView;
        }
    }
}
