package com.ecloud.rcsd.base;

import android.support.v4.app.Fragment;

import com.ecloud.rcsd.R;
import com.runer.liabary.dialog.ProgressHUD;
import com.runer.liabary.util.UiUtil;
import com.runer.net.interf.INetResult;

/**
 * Rcsd
 * Create   2017/5/11 13:35;
 * https://github.com/szhua
 * @author sz.hua
 */
public  class BaseFragment extends Fragment implements INetResult{



    private ProgressHUD mProgressHUD;

    @Override
    public void onRequestSuccess(int requestCode) {
         showProgress(false);
    }

    @Override
    public void onRequestError(int requestCode, String errorInfo, int error_code) {
showProgress(false);
        UiUtil.showLongToast(getContext(),errorInfo);
    }

    @Override
    public void onRequestFaild(int requestCode, String errorNo, String errorMessage) {
      showProgress(false);
        UiUtil.showLongToast(getContext(),errorNo);
    }

    @Override
    public void onNoConnect() {
       showProgress(false);
        UiUtil.showLongToast(getContext(),getString(R.string.no_net));
    }

    public void showProgress(boolean show){
        showProgressWithMsg(show,getString(R.string.loading));
    }
    /* 显示加载进度条*/
    public void showProgressWithMsg(boolean show ,String msg) {

        if (show) {
            mProgressHUD = ProgressHUD.show(getContext(),msg ,true,true,null);
        } else {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
        }
    }
}
