package com.ecloud.rcsd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecloud.rcsd.R;

/**
 * Rcsd
 * Create   2017/5/12 17:04;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class HomeMineMsgAdapter extends BaseAdapter {


    @Override
    public int getCount() {
        return 3;
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_mine_msg_layout,parent,false);
        return convertView;
    }
}
