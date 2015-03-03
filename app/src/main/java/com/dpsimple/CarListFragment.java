package com.dpsimple;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dpsimple.models.CarModel;
import com.dpsimple.models.Repo;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarListFragment extends Fragment {


    private ListView listView;

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cars_list_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) view.findViewById(R.id.cars_list);
        listView.setAdapter(new CarsAdapter(getActivity(), 0, Repo.detailModelsRepoMonth));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                CarModel selectedItem = (CarModel) adapterView.getItemAtPosition(i);
                bundle.putParcelable(CarDetailFragment.OBJECT, selectedItem);
                ((Item1Activity) getActivity()).gotoFragmentWithInitialSavedState(
                        CarDetailFragment.class, bundle);

            }
        });
    }

    private class CarsAdapter extends ArrayAdapter<CarModel> {
        public CarsAdapter(Context context, int resource, List<CarModel> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView textView = new TextView(getContext());
            textView.setText(getItem(position).getName());
            return textView;


        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
