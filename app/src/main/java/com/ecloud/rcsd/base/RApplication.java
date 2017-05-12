package com.ecloud.rcsd.base;

import android.app.Application;


import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Rcsd
 * Create   2017/5/11 11:17;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class RApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger
        .init(Constant.TAG)
        .logLevel(LogLevel.FULL);        // default LogLevel.FULL == Note: Use LogLevel.NONE for the release versions.

        UMShareAPI.get(this);
        Config.DEBUG = true;
        UMShareAPI.init(this, "59094e1065b6d67da2001d62");

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        PlatformConfig.setWeixin("wx09ae8c74b025e0eb", "9b42cad8978413a98bcebf9179a77d66");
        PlatformConfig.setSinaWeibo("1725265599", "1489dbc235e9c333b8f941ee687c8823","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1106112746", "mwCuRc8H32giOoDK");
    }
}
