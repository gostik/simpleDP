package com.dpsimple.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dpsimple.BaseActivity;
import com.dpsimple.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user_sca on 26.02.2015.
 */
public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        Bundle arguments = getArguments();
        return (arguments != null) ? arguments.getInt("index", 0) : 0;
    }

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED,
            ATTRIBUTE_NAME_IMAGE};
    // массив ID View-компонентов, в которые будут вставлять данные
    int[] to = {R.id.tvText, R.id.cbChecked, R.id.ivImg};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView scroller = new ScrollView(getActivity());
//        TextView text = new TextView(getActivity());
//        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
//                4, getActivity().getResources().getDisplayMetrics());
//        text.setPadding(padding, padding, padding, padding);
//        scroller.addView(text);
//        text.setText("" + getShownIndex());

        ListView listView = new ListView(getActivity());
        String[] texts = {"sometext 1", "sometext 2", "sometext 3",
                "sometext 4", "sometext 5"};
        boolean[] checked = {true, false, false, true, false};
        int img = R.drawable.ic_launcher;
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(
                texts.length);
        Map<String, Object> m;
        for (int i = 0; i < texts.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            m.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            m.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(m);
        }
        SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.item,
                from, to);
        listView.setAdapter(sAdapter);
        listView.setClickable(true);
        listView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((BaseActivity)getActivity()).openActivity(0);
            }
        });
        scroller.addView(listView);
        return scroller;
    }
}
