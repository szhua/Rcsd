package com.ecloud.rcsd.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.base.BaseListAdapter;

/**
 * Rcsd
 * Create   2017/5/11 19:27;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class HomeListAdapter extends BaseListAdapter<String> {
    public HomeListAdapter(Context context) {
        super(context);
    }
    @Override
    public int getConvertViewId() {
        return R.layout.item_home_layout;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View convertView, int pos) {
        ViewHolder holder =new ViewHolder(convertView);
        return holder;
    }

    private class ViewHolder extends  BaseListAdapter<String>.BaseViewHolder{
        private TextView tv ;
        public ViewHolder(View itemView) {
            super(itemView);
          //  tv = (TextView) itemView.findViewById(R.id.text);
        }
        @Override
        public void showData(int position, String o, Context context) {
          //  tv.setText(o);
        }
    }


}
