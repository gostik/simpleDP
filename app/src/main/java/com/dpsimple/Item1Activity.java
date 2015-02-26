package com.dpsimple;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author MZ
 *
 */
public class Item1Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		getLayoutInflater().inflate(R.layout.activity_master_details, frameLayout);
		
		/**
		 * Setting title and itemChecked  
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);

	}
}
