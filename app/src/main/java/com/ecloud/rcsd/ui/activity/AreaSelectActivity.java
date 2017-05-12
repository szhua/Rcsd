package com.ecloud.rcsd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.adapter.AreaAdapter;
import com.ecloud.rcsd.base.BaseAcitivty;
import com.ecloud.rcsd.bean.AreaBean;
import com.runer.liabary.util.UiUtil;
import com.runer.liabary.widget.NoScrollListView;
import com.runer.net.RequestCode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AreaSelectActivity extends BaseAcitivty {

    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.area_list)
    NoScrollListView areaList;

    private AreaAdapter areaAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_select);
        ButterKnife.inject(this);
        areaAdapter =new AreaAdapter(this) ;
        areaAdapter.setDatas(getData());
        areaList.setAdapter(areaAdapter);

        areaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               AreaBean data = (AreaBean) areaAdapter.getItem(position);
                if("1".equals(data.getType())){
                    Intent intent =new Intent() ;
                    intent.putExtra("name",data.getName());
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    UiUtil.showLongToast(getApplicationContext(),"未开通");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("地区选择");
    }

    public List<AreaBean> getData(){
        List<AreaBean> datas =new ArrayList<>() ;
        for (int i = 0; i <20 ; i++) {
            AreaBean bean =new AreaBean() ;
            if(i<10){

                bean.setName("Item"+i);
                bean.setType("1");
            }else{
                bean.setName("IteM"+i);
                bean.setType("2");
            }
            datas.add(bean);
        }
        return  datas ;
    }
}
