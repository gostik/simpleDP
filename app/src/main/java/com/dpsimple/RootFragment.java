package com.dpsimple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RootFragment extends Fragment {

    /**
     * Created by user_sca on 02.03.2015.
     */

	private static final String TAG = "RootFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.root_fragment, container, false);

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		transaction.replace(R.id.root_frame, new CarListFragment());

		transaction.commit();

		return view;
	}

}
