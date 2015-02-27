package com.dpsimple.fragments;

import android.os.Bundle;

import com.dpsimple.BaseActivity;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDescriptionActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new CarDescriptionFragment()).commit();
    }
}
