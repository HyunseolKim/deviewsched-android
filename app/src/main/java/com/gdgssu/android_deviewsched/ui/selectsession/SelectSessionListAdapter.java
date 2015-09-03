package com.gdgssu.android_deviewsched.ui.selectsession;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.gdgssu.android_deviewsched.model.Day;

public class SelectSessionListAdapter extends BaseAdapter {

    private Day dayItem;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setDayItem(Day dayItem) {
        this.dayItem = dayItem;
    }

}
