package com.dpsimple;

import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author MZ
 *
 */
public class Item5Activity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.activity_main, frameLayout);


		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);
		
		((ImageView)findViewById(R.id.image_view)).setBackgroundResource(R.drawable.image5);
	}
}
