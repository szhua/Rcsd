package com.ecloud.rcsd.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.adapter.HomeMineMsgAdapter;
import com.ecloud.rcsd.base.BaseFragment;
import com.ecloud.rcsd.ui.activity.UserCenterActivity;
import com.ecloud.rcsd.util.RcUtil;
import com.runer.liabary.widget.NoScrollListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Rcsd
 * Create   2017/5/11 14:57;
 * https://github.com/szhua
 * @author sz.hua
 */
public class MineFragment extends BaseFragment {

    @InjectView(R.id.setting_bt)
    ImageView settingBt;
    @InjectView(R.id.header_icon)
    CircleImageView headerIcon;
    @InjectView(R.id.user_name_tv)
    TextView userNameTv;
    @InjectView(R.id.edit_bt)
    TextView editBt;
    @InjectView(R.id.notice_msg_bt)
    LinearLayout noticeMsgBt;
    @InjectView(R.id.message_list)
    NoScrollListView messageList;
    @InjectView(R.id.shenfenyanzheng_bt)
    TextView shenfenyanzhengBt;
    @InjectView(R.id.wodewenti_bt)
    TextView wodewentiBt;
    @InjectView(R.id.wodeshoucang_bt)
    TextView wodeshoucangBt;
    @InjectView(R.id.yijianfankui_bt)
    TextView yijianfankuiBt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_layout, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messageList.setAdapter(new HomeMineMsgAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.setting_bt, R.id.header_icon, R.id.user_name_tv, R.id.edit_bt, R.id.notice_msg_bt, R.id.shenfenyanzheng_bt, R.id.wodewenti_bt, R.id.wodeshoucang_bt, R.id.yijianfankui_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_bt:
                break;
            case R.id.header_icon:
                RcUtil.tranUi(getContext(), UserCenterActivity.class);
                break;
            case R.id.user_name_tv:
                break;
            case R.id.edit_bt:
                break;
            case R.id.notice_msg_bt:
                break;
            case R.id.shenfenyanzheng_bt:
                break;
            case R.id.wodewenti_bt:
                break;
            case R.id.wodeshoucang_bt:
                break;
            case R.id.yijianfankui_bt:
                break;
        }
    }
}
