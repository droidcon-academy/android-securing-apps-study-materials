package com.preemptive.accountmanager;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {
    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView)super.getView(position, convertView, parent);
        view.setTextSize( TypedValue.COMPLEX_UNIT_DIP, 25.0f);
        MenuItem item = getItem(position);
        view.setEnabled(item.enabled);

        if ( item.enabled ) {
            view.setOnClickListener(item.onClick);
        }
        return view;
    }
}

