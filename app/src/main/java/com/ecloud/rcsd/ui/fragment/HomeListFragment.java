package com.ecloud.rcsd.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.BaseAdapter;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.adapter.HomeListAdapter;
import com.ecloud.rcsd.base.BaseLoadMoreFragment;
import com.ecloud.rcsd.widget.AdViewPager;
import com.orhanobut.logger.Logger;
import com.runer.liabary.util.UiUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Rcsd
 * Create   2017/5/11 15:42;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class HomeListFragment extends BaseLoadMoreFragment {

    private HomeListAdapter homeListAdapter ;
    private List<String>datas ;
    private AdViewPager adViewPager ;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setheaderView(R.layout.home_header_layout);
        adViewPager = (AdViewPager) findHeaderView(R.id.adviewpager);
        super.onViewCreated(view, savedInstanceState);
    }




    @Override
    public void onRefreh() {
        UiUtil.showLongToast(getContext(),"refresh");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(Long aLong) {
                        Logger.d(aLong);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e);
                    }
                    @Override
                    public void onComplete() {
                        onCompeleteRefresh();
                        if(adViewPager!=null){
                            adViewPager.setBannerEntities();
                        }
                        datas =getData() ;
                        homeListAdapter.setDatas(datas);
                    }
                }) ;
    }

    @Override
    public void onLoadMore() {
        UiUtil.showLongToast(getContext(),"loadmore");
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Long aLong) {
                        Logger.d(aLong);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e);
                    }

                    @Override
                    public void onComplete() {
                        onCompeleteRefresh();
                        datas =new ArrayList<>() ;
                        homeListAdapter.setDatas(datas);
                    }
                }) ;
    }

    @Override
    public BaseAdapter getAdapter() {
        homeListAdapter =new HomeListAdapter(getContext());
         homeListAdapter.setDatas(datas);
        return homeListAdapter;
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("ITEM" + i);
        }
        return data;
    }
}
