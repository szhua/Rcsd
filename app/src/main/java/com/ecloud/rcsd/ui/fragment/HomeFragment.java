package com.ecloud.rcsd.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.base.BaseFragment;
import com.ecloud.rcsd.ui.activity.AreaSelectActivity;
import com.ecloud.rcsd.util.RcUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Rcsd
 * Create   2017/5/11 14:25;
 * https://github.com/szhua
 * @author sz.hua
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.change_area_bt)
    TextView changeAreaBt;
    @InjectView(R.id.serch_bt)
    TextView serchBt;
    @InjectView(R.id.home_list_container)
    LinearLayout homeListContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager =getChildFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.home_list_container,new HomeListFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    private static  final  int REQUEST_CODE =101 ;
    @OnClick({R.id.change_area_bt, R.id.serch_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.change_area_bt:
                Intent intent =new Intent(getContext(),AreaSelectActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.serch_bt:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE&&resultCode==getActivity().RESULT_OK){
         if(data!=null){
             changeAreaBt.setText("人才"+data.getStringExtra("name"));
         }
        }
    }
}
