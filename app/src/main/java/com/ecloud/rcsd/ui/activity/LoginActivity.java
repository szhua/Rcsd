package com.ecloud.rcsd.ui.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.RebackCode;
import com.ecloud.rcsd.base.BaseAcitivty;
import com.ecloud.rcsd.dao.LoginDao;
import com.ecloud.rcsd.util.RcUtil;
import com.ecloud.rcsd.widget.ShareBord;
import com.runer.liabary.util.UiUtil;
import com.runer.net.RequestCode;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseAcitivty {

    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.username)
    EditText usernameet;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.login_bt)
    TextView loginBt;
    @InjectView(R.id.forget_pass)
    TextView forgetPass;
    @InjectView(R.id.register)
    TextView register;
    @InjectView(R.id.wechat_login)
    ImageView wechatLogin;
    @InjectView(R.id.qqlogin)
    ImageView qqlogin;
    @InjectView(R.id.sinalogin)
    ImageView sinalogin;

    private PopupWindow popupWindow;

    private LoginDao logInDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        logInDao =new LoginDao(this,this) ;
    }
    @Override
    protected void onStart() {
        super.onStart();
        setTitle("登录");
    }
    private void showShareView() {
        ShareBord shareBord = new ShareBord(this);
        popupWindow = new PopupWindow(shareBord,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popWindow_animation);
        // 点击弹出
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onRequestSuccess(int requestCode) {
        super.onRequestSuccess(requestCode);
        if(requestCode== RequestCode.CODE_LOGIN){
            UiUtil.showLongToast(this,"登录成功");
            RcUtil.setUserId(this,logInDao.getUser_id());
            finish();
        }
    }
    @Override
    public void onRequestError(int requestCode, String errorInfo, int error_code) {
        showProgress(false);
        if (error_code == RebackCode.PASS_ERRO) {
            UiUtil.showLongToast(this, "密码错误");
        } else if (error_code == RebackCode.USER_NOT_EXIT) {
            UiUtil.showLongToast(this, "用户名错误");
        }
    }

    private String pass ;
    private String username;
    private boolean checkLogin(){
        pass =password.getText().toString() ;
        username =usernameet.getText().toString() ;
        if(TextUtils.isEmpty(username)){
            UiUtil.showLongToast(this,"请填写用户名");
            return  false ;
        }
        if(TextUtils.isEmpty(pass)){
            UiUtil.showLongToast(this,"密码不能为空");
            return false ;
        }
        return true ;
    }

    @OnClick({R.id.login_bt, R.id.forget_pass, R.id.register, R.id.wechat_login, R.id.qqlogin, R.id.sinalogin})
    public void onClick(View view) {
        switch (view.getId()) {
            //登录
            case R.id.login_bt:
                if(checkLogin()){
                    logInDao.logIn(username,pass);
                    showProgressWithMsg(true,"正在登录");
                }
                break;
            case R.id.forget_pass:
                break;
            case R.id.register:
                RcUtil.tranUi(this,RgisterActivity.class);
                break;
            case R.id.wechat_login:
                break;
            case R.id.qqlogin:
                break;
            case R.id.sinalogin:
                break;
        }
    }
}
