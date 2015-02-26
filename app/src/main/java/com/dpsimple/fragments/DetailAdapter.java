package com.dpsimple.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dpsimple.R;
import com.dpsimple.models.Model;

import java.util.List;

/**
 * Created by user_sca on 26.02.2015.
 */
public class DetailAdapter extends ArrayAdapter<Model> {
    public DetailAdapter(Context context, int resource, List<Model> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        View inflated = layoutInflater.inflate(R.layout.details_list_item, null);

        TextView viewById = (TextView) inflated.findViewById(R.id.name);

        viewById.setText(getItem(position).getName());

        return viewById;
    }
}
