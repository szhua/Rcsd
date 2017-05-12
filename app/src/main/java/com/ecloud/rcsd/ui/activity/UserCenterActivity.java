package com.ecloud.rcsd.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecloud.rcsd.R;
import com.ecloud.rcsd.base.BaseAcitivty;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.FunctionOptions;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserCenterActivity extends BaseAcitivty {

    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.header_icon)
    CircleImageView headerIcon;
    @InjectView(R.id.header_bt)
    LinearLayout headerBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        ButterKnife.inject(this);

        FunctionOptions options = new FunctionOptions.Builder()
                .setType(FunctionConfig.TYPE_IMAGE)
        .setCompress(true)
        .setGrade(Luban.THIRD_GEAR)
        .setMaxSelectNum(1)
        .create();
        PictureConfig.getInstance().init(options);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("个人中心");
    }

    @OnClick({R.id.header_icon, R.id.header_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_icon:
                PictureConfig.getInstance().openPhoto(this, new PictureConfig.OnSelectResultCallback() {
                    @Override
                    public void onSelectSuccess(List<LocalMedia> list) {
                        Glide.with(UserCenterActivity.this).load(list.get(0).getCompressPath()).into(headerIcon);
                    }
                    @Override
                    public void onSelectSuccess(LocalMedia localMedia) {
                        Glide.with(UserCenterActivity.this).load(localMedia.getCompressPath()).into(headerIcon);
                    }
                });

                break;
            case R.id.header_bt:
                break;
        }
    }
}

