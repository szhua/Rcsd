package com.ecloud.rcsd.dao;

import android.content.Context;

import com.ecloud.rcsd.base.RequstUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.loopj.android.http.RequestParams;
import com.orhanobut.logger.Logger;
import com.runer.net.IDao;
import com.runer.net.RequestCode;
import com.runer.net.interf.INetResult;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Rcsd
 * Create   2017/5/11 18:08;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class TestDao extends IDao {
    public static String URL="http://www.shopgo365.com/api/user/index.php";
    public static String CurrentLongitude = "116.984064";
    public static String CurrentLatitude = "36.657193";
    public TestDao(Context context, INetResult iNetResult) {
        super(context, iNetResult);
    }
    @Override
    public void onRequestSuccess(JsonNode result, int requestCode) throws IOException {
        Logger.json(result.toString());
    }

    public void Login(String name ,String password){

        RequestParams params =new RequestParams() ;
        params.put("name",name);
        params.put("password",password);

        originalPostRequest(RequstUrl.BASE_URL+RequstUrl.APP_LOGIN,params ,RequestCode.CODE_0);

    }



    // for md5 ;
    public  String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null ;
        }
    }

}
