package com.ecloud.rcsd.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ecloud.rcsd.R;
import com.runer.liabary.util.UiUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Rcsd
 * Create   2017/5/12 9:01;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class ShareBord  extends LinearLayout implements View.OnClickListener{



    public ShareBord(Context context){
        this(context,null);
    }


    public ShareBord(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.um_share_view, this);
    }

    private LinearLayout weibo;
    private LinearLayout qq;
    private LinearLayout wechat;
    private LinearLayout qzone;
    private LinearLayout wxcircle;
    private View quitBt;
    private UMWeb web;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        weibo = (LinearLayout) findViewById(R.id.weibo);
        qq = (LinearLayout) findViewById(R.id.qq);
        wechat = (LinearLayout) findViewById(R.id.wechat);
        qzone = (LinearLayout) findViewById(R.id.qzone);
        wxcircle = (LinearLayout) findViewById(R.id.wxcircle);
        quitBt =findViewById(R.id.quit_bt);

        weibo.setOnClickListener(this);
        qq.setOnClickListener(this);
        wechat.setOnClickListener(this);
        qzone.setOnClickListener(this);
        wxcircle.setOnClickListener(this);
        quitBt.setOnClickListener(this);
    }

    public interface OnquitListener{
        void cancle ();
    }


    private OnquitListener onquitListener ;

    public void setOnquitListener(OnquitListener onquitListener) {
        this.onquitListener = onquitListener;
    }



    private Activity activity ;


    public void setDataConent(Activity activity ,String shareUrl ,
                              String title ,
                              UMImage thumb ,
                              String des )
    {

        this.activity =activity ;
        web = new UMWeb(shareUrl);
        web.setTitle(title);//标题
        //	web.setThumb(thumb);  //缩略图
        if(!TextUtils.isEmpty(des))
            web.setDescription(des);//描述

    }

    @Override
    public void onClick(View v) {
        if(v==wechat){
            new ShareAction(activity)
                    .setPlatform(SHARE_MEDIA.WEIXIN)
                    .withMedia(web)
                    .setCallback(new UmShareCallBackListener(getContext()))
                    .share();
        }else if(v==qq){
            new ShareAction(activity)
                    .setPlatform(SHARE_MEDIA.QQ)
                    .withMedia(web)
                    .setCallback(new UmShareCallBackListener(getContext()))
                    .share();
        }else if(v==weibo){
            new ShareAction(activity)
                    .setPlatform(SHARE_MEDIA.SINA)
                    .withMedia(web)
                    .setCallback(new UmShareCallBackListener(getContext()))
                    .share();
        }else if(v==quitBt){
            if(onquitListener!=null){
                onquitListener.cancle();
            }

        }else if(v==wxcircle){
            new ShareAction(activity)
                    .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                    .withMedia(web)
                    .setCallback(new UmShareCallBackListener(getContext()))
                    .share();
        }else if(v==qzone){
            new ShareAction(activity)
                    .setPlatform(SHARE_MEDIA.QZONE)
                    .withMedia(web)
                    .setCallback(new UmShareCallBackListener(getContext()))
                    .share();
        }
    }

    public class UmShareCallBackListener implements UMShareListener {

        private Context context;

        public UmShareCallBackListener(Context context) {
            this.context = context;
        }

        @Override
        public void onCancel(SHARE_MEDIA arg0) {

            UiUtil.showLongToast(context,  context.getResources().getString(R.string.share_cancle));


            if(onquitListener!=null){
                onquitListener.cancle();
            }
        }

        @Override
        public void onError(SHARE_MEDIA arg0, Throwable arg1) {
            UiUtil.showLongToast(context,  context.getResources().getString(R.string.share_error));

            if(onquitListener!=null){
                onquitListener.cancle();
            }
        }

        @Override
        public void onResult(SHARE_MEDIA arg0) {
            UiUtil.showLongToast(context,  context.getResources().getString(R.string.share_success));

            if(onquitListener!=null){
                onquitListener.cancle();
            }

        }

        @Override
        public void onStart(SHARE_MEDIA arg0) {
            UiUtil.showLongToast(context,  context.getResources().getString(R.string.share_start));
            if(onquitListener!=null){
                onquitListener.cancle();
            }
        }

    }
}
