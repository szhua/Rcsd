package com.ecloud.rcsd.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Rcsd
 * Create   2017/5/11 14:56;
 * https://github.com/szhua
 * @author sz.hua
 */
public class DeclareFragment extends BaseFragment {
    @InjectView(R.id.container)
    LinearLayout container;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_declare_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager = getChildFragmentManager() ;
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction() ;
        fragmentTransaction.add(R.id.container,new HomeListFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
