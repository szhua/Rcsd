package com.ecloud.rcsd.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.ecloud.rcsd.base.Constant;

/**
 * Rcsd
 * Create   2017/5/11 14:09;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public final class RcUtil {

  //是否登录
    public static  boolean isLogin(Context context){
        if(TextUtils.isEmpty(SharedPrefsUtil.getValue(context,Constant.USER_ID_KEY,""))){
            return  false ;
        }
        return  true ;
    }

    public static boolean isLogin(Context context ,boolean toLoginUi){

        if(isLogin(context)){
            return  true ;
        }else{
            if(toLoginUi){
                //TODO
                return  false ;
            }else{
                return  false ;
            }
        }

    }


    public static  void setUserId(Context context , String userId){
        SharedPrefsUtil.putValue(context , Constant.USER_ID_KEY,userId);
    }

    public static void clearUserId(Context context ){
        SharedPrefsUtil.putValue(context, Constant.USER_ID_KEY,"");
    }

    public static void tranUi(Context context , Class uiClass){
        Intent intent =new Intent(context,uiClass);
        context.startActivity(intent);
    }
}
