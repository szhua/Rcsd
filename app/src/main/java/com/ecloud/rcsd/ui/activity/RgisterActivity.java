package com.ecloud.rcsd.ui.activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ecloud.rcsd.R;
import com.ecloud.rcsd.base.BaseAcitivty;
import com.ecloud.rcsd.widget.CodeButton;
import com.runer.liabary.util.UiUtil;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
//注册界面
public class RgisterActivity extends BaseAcitivty {

    @InjectView(R.id.left_back)
    ImageView leftBack;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.right_text)
    TextView rightText;
    @InjectView(R.id.right_img)
    ImageView rightImg;
    @InjectView(R.id.username_et)
    EditText usernameEt;
    @InjectView(R.id.password_et)
    EditText passwordEt;
    @InjectView(R.id.code_input_et)
    EditText codeInputEt;
    @InjectView(R.id.code_bt)
    CodeButton codeBt;
    @InjectView(R.id.reigter_bt)
    TextView reigterBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgister);
        ButterKnife.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle("注册");
    }

    @OnClick({R.id.code_bt, R.id.reigter_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.code_bt:
                if(checkForCode()){
                    //请求逻辑后执行；
                    codeBt.startNumCode();
                    UiUtil.showLongToast(this,"coming");
                }
                break;
            case R.id.reigter_bt:
                if(checkInput()){
                    UiUtil.showLongToast(this,"coming");
                }
                break;
        }
    }

    private String phone ;
    private String pass ;
    private String code ;

    private boolean checkInput(){

         if(checkForCode()){
             code =codeInputEt.getText().toString() ;
             if(TextUtils.isEmpty(code)){
                 UiUtil.showLongToast(this,"请输入验证码");
                 return  false ;
             }
         }
        return  true ;
    }

    private  boolean  checkForCode(){
        phone =usernameEt.getText().toString() ;
        pass =passwordEt.getText().toString() ;

        if(TextUtils.isEmpty(phone)){
            UiUtil.showLongToast(this,"请输入手机号");
            return  false ;
        }
        if(!UiUtil.isValidMobileNo(phone)){
            UiUtil.showLongToast(this,"手机号格式不正确");
            return  false ;
        }

        if(TextUtils.isEmpty(pass)){
            UiUtil.showLongToast(this,"请输入密码");
            return  false ;
        }

        if(pass.length()<6||pass.length()>16){
            UiUtil.showLongToast(this,"密码长度为6-16位");
            return  false ;
        }

        return  true ;
    }



}
