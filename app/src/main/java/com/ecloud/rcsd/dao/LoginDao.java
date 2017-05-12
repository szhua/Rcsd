package com.ecloud.rcsd.dao;

import android.content.Context;

import com.ecloud.rcsd.base.RequstUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.loopj.android.http.RequestParams;
import com.runer.net.IDao;
import com.runer.net.JsonUtil;
import com.runer.net.RequestCode;
import com.runer.net.interf.INetResult;

import java.io.IOException;

/**
 * Rcsd
 * Create   2017/5/12 13:47;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class LoginDao extends IDao {

    private String user_id ;

    public String getUser_id() {
        return user_id;
    }

    public LoginDao(Context context, INetResult iNetResult) {
        super(context, iNetResult);
    }

    @Override
    public void onRequestSuccess(JsonNode result, int requestCode) throws IOException {
      try{
          user_id =result.findValue("user_id").asText();
      }catch (Exception e){e.printStackTrace();}

    }

    /*登录请求*/
    public void logIn(String name ,String password){
            user_id ="" ;
            RequestParams params =new RequestParams() ;
            params.put("name",name);
            params.put("password",password);
            originalPostRequest(RequstUrl.BASE_URL+RequstUrl.APP_LOGIN,params , RequestCode.CODE_LOGIN);
    }

}
