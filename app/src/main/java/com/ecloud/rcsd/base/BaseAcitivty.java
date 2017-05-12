package com.ecloud.rcsd.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecloud.rcsd.R;
import com.runer.liabary.dialog.ProgressHUD;
import com.runer.liabary.util.UiUtil;
import com.runer.net.interf.INetResult;

/**
 * Rcsd
 * Create   2017/5/11 11:52;
 * https://github.com/szhua
 *基础Activity
 * @author sz.hua
 */
public class BaseAcitivty extends AppCompatActivity implements INetResult {

   private TextView title ;
   private ImageView leftImage ;
   private ImageView rightImage ;
   private TextView rightTv ;
   private ProgressHUD mProgressHUD;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void  setTitle(@NonNull String title){
        if(this.title!=null&& !TextUtils.isEmpty(title)){
            this.title.setText(title);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        title = (TextView) findViewById(R.id.title);
        leftImage = (ImageView) findViewById(R.id.left_back);
        rightImage = (ImageView) findViewById(R.id.right_img);
        rightTv = (TextView) findViewById(R.id.right_text);

        if(leftImage!=null) {
            leftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }


    @Override
    public void onRequestSuccess(int requestCode) {
showProgress(false);
    }

    @Override
    public void onRequestError(int requestCode, String errorInfo, int error_code) {
        UiUtil.showLongToast(this,errorInfo);
        showProgress(false);
    }

    @Override
    public void onRequestFaild(int requestCode, String errorNo, String errorMessage) {
       UiUtil.showLongToast(this,errorNo);
        showProgress(false);
    }

    @Override
    public void onNoConnect() {
        UiUtil.showLongToast(this,getString(R.string.no_net));
        showProgress(false);
    }



    public void setRightTextColor(@ColorRes int color){
        if(rightTv!=null){
            rightTv.setTextColor(ContextCompat.getColor(this, color));
        }
    }



    //设置可见返回
    public void setLeftImageVisible(int visible){
        if(leftImage!=null){
            if(visible==View.VISIBLE||visible==View.INVISIBLE||visible==View.GONE)
            leftImage.setVisibility(visible);
        }
    }
    //设置右标题
    public void setRightText(@NonNull  String rightText){
        if (rightTv!=null&&!TextUtils.isEmpty(rightText)) {
            rightTv.setVisibility(View.VISIBLE);
            rightTv.setText(rightText);
        }
    }

    //设置右图片
    public void setRightImage(@DrawableRes int imgRes){
        if (rightImage!=null){
            rightImage.setVisibility(View.VISIBLE);
            rightImage.setImageResource(imgRes);
        }
    }
    //设置左标题
    public  void setLeftImage(@DrawableRes int imgRes){
        if(leftImage!=null){
            leftImage.setVisibility(View.VISIBLE);
            leftImage.setImageResource(imgRes);
        }
    }

    //设置左边点击
    public  void setLeftImageClickListener(View.OnClickListener clickListener){
        if(leftImage!=null){
            leftImage.setOnClickListener(clickListener);
        }
    }

    //设置右边点击
    public void setRightImageClickListener(View.OnClickListener clickListener){
        if(rightImage!=null){
            rightImage.setOnClickListener(clickListener);
        }
    }
    //设置右边点击
    public void setRightTvClickLister(View.OnClickListener clickListener){
        if(rightTv!=null){
            rightTv.setOnClickListener(clickListener);
        }
    }

public void showProgress(boolean show){
    showProgressWithMsg(show,getString(R.string.loading));
}
    /* 显示加载进度条*/
    public void showProgressWithMsg(boolean show ,String msg) {
        if (show) {
            mProgressHUD = ProgressHUD.show(this,msg ,true,true,null);
        } else {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
        }
    }

}
