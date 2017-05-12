package com.ecloud.rcsd.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecloud.rcsd.R;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Rcsd
 * Create   2017/5/11 18:43;
 * https://github.com/szhua
 *BaseListAdapter;
 * @author sz.hua
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    public static  final int EMPTY_TYPE =1 ;
    public static  final int DATA_TYPE =2 ;


    public  List<T> datas ;

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }



    public Context context ;
    public LayoutInflater inflater ;
    public BaseListAdapter(Context context){
        this.context =context ;
        inflater =LayoutInflater.from(context) ;
    }

    @Override
    public int getItemViewType(int position) {
        if(datas==null||datas.isEmpty()){
            return  EMPTY_TYPE ;
        }
        return DATA_TYPE;
    }

    @Override
    public int getCount(){
        //为空的时候返回emptyView;
        if(datas==null||datas.isEmpty()){
            return  1 ;
        }else{
            return  datas.size() ;
        }
    }

    @Override
    public Object getItem(int position) {
        if(datas==null||datas.isEmpty()){
            return  null ;
        }
        return datas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //获得item LayoutID；
    public  abstract  int getConvertViewId();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          int type =getItemViewType(position);

          if(type==EMPTY_TYPE){
              //复用与都进行重新创建
              convertView = inflater.inflate(R.layout.item_empty_layout, parent, false);
              convertView.getLayoutParams().height = parent.getHeight();
          }else{
              BaseViewHolder baseViewHolder =null;
              if(convertView==null||convertView.getTag()==null){
                  Logger.d("create");
                  convertView =inflater.inflate(getConvertViewId(),parent,false);
                  baseViewHolder =onCreateViewHolder(convertView,position);
                  convertView.setTag(baseViewHolder);
              }else{
                  baseViewHolder = (BaseViewHolder) convertView.getTag();
              }
                  baseViewHolder.showData(position,datas.get(position),context);

          }
        return  convertView ;
    }


    //创建viewholder；
    public abstract  BaseViewHolder onCreateViewHolder(View convertView ,int pos );

   //BaseViewHodler
    public abstract class BaseViewHolder {

        public BaseViewHolder(View itemView){
        }

        public abstract void showData(int position, T t, Context context) ;
    }





}
