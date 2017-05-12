package com.ecloud.rcsd.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ecloud.rcsd.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Rcsd
 * Create   2017/5/11 13:51;
 * https://github.com/szhua
 * @author sz.hua
 */
public abstract class BaseLoadMoreFragment extends BaseFragment implements PullToRefreshBase.OnRefreshListener2 {

    @InjectView(R.id.listview)
    public  PullToRefreshListView listview;
    private static final PullToRefreshBase.Mode defaulMode = PullToRefreshBase.Mode.BOTH;
    private View headerView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.base_fragment_loadmore, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    public void setListMode(PullToRefreshBase.Mode mode){
        listview.setMode(mode);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listview.setMode(PullToRefreshBase.Mode.BOTH);
        listview.setOnRefreshListener(this);

        if(headerView!=null){
            listview.getRefreshableView().addHeaderView(headerView);
        }

        if(getAdapter()!=null)
        listview.setAdapter(getAdapter());

    }

    //findheader
    public View findHeaderView(@IdRes int id){
        if (headerView!=null)
        return  headerView.findViewById(id);
        else
            return  null;
    }

    //setHeader
     public View setheaderView(@LayoutRes int layout){
         headerView=View.inflate(getContext(),layout,null) ;
        return  headerView ;
     }
     public  abstract  void onRefreh();
     public   abstract  void onLoadMore();
     public abstract BaseAdapter getAdapter();

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
       onRefreh();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        onLoadMore();
    }
    //完成刷新
    public void onCompeleteRefresh(){
        if(listview!=null){
            listview.post(new Runnable() {
                @Override
                public void run() {
               listview.onRefreshComplete();
                }
            });
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
