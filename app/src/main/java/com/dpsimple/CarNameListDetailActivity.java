package com.dpsimple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.dpsimple.fragments.CarDetailFragment;
import com.dpsimple.fragments.CarListFragment;
import com.dpsimple.fragments.DualPaneActivity;
import com.dpsimple.models.CarModel;

/**
 * @author MZ
 */
public class CarNameListDetailActivity extends DualPaneActivity<CarListFragment, CarDetailFragment> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLeftFragment(new CarListFragment());

    }

    @Override
    protected void onResume() {
        super.onResume();
        getLeftFragment().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CarModel item = (CarModel) adapterView.getAdapter().getItem(i);

                if (!isTablet()) {
                    startActivity(item);
                } else
                    setRightFragment(CarDetailFragment.getInstanse(item));
            }
        });
    }

    private void startActivity(CarModel item) {
        Intent intent = new Intent(this, CarDetailActivity.class);
        intent.putExtra(CarDetailActivity.OBJECT, item);
        startActivity(intent);
    }

}
