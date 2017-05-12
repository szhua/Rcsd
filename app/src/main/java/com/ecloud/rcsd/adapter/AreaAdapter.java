package com.ecloud.rcsd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.bean.AreaBean;

import java.util.List;

/**
 * Rcsd
 * Create   2017/5/12 18:22;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class AreaAdapter extends BaseAdapter {

    List<AreaBean> datas ;

    private Context context ;
    private LayoutInflater inflater ;
    public AreaAdapter(Context context) {
        this.context =context ;
        inflater=LayoutInflater.from(context);
    }

    public void setDatas(List<AreaBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView =inflater.inflate(R.layout.item_area_layout,parent,false) ;
        AreaBean data =datas.get(position);
        if("1".equals(data.getType())){
            convertView.findViewById(R.id.has_got).setVisibility(View.VISIBLE);
            convertView.findViewById(R.id.not_got).setVisibility(View.GONE);
            ((TextView) convertView.findViewById(R.id.area_name_has_got)).setText(data.getName());
        }else {
            convertView.findViewById(R.id.has_got).setVisibility(View.GONE);
            convertView.findViewById(R.id.not_got).setVisibility(View.VISIBLE);
            ((TextView) convertView.findViewById(R.id.area_name_not_got)).setText(data.getName());
        }

        return convertView;
    }
}
