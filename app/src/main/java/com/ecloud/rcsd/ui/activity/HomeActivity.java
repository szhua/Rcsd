package com.ecloud.rcsd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import com.ecloud.rcsd.R;
import com.ecloud.rcsd.base.BaseAcitivty;
import com.ecloud.rcsd.ui.fragment.DeclareFragment;
import com.ecloud.rcsd.ui.fragment.HomeFragment;
import com.ecloud.rcsd.ui.fragment.MineFragment;
import com.ecloud.rcsd.util.RcUtil;
import com.runer.liabary.util.UiUtil;
import com.umeng.socialize.UMShareAPI;
import butterknife.ButterKnife;
import butterknife.InjectView;
public class HomeActivity extends BaseAcitivty implements TabHost.OnTabChangeListener {

    @InjectView(android.R.id.tabhost)
    FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        ButterKnife.inject(this);


         /*set up tab with fragment*/
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setBackgroundResource(R.color.white);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.getTabWidget().setBackgroundResource(R.color.white);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(getTabItemView(R.drawable.home__dynamic_selector, "首页")),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("decalare").setIndicator(getTabItemView(R.drawable.home__dynamic_selector, "申报")),
                DeclareFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getTabItemView(R.drawable.home__dynamic_selector, "我的")),
                MineFragment.class, null);

        mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = (int) getResources().getDimension(R.dimen.tab_height);

        mTabHost.setOnTabChangedListener(this);
    }

  /*create tabLayout from drawable and title*/
    private View getTabItemView(int id, String title) {
        View view = getLayoutInflater().inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(id);
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(title);
        return view;
    }

    /*select tab index */
    public void setCurrentTab(int index) {
        if (mTabHost != null) {
            mTabHost.setCurrentTab(index);
        }
    }

    private static  final long waitTime = 2000;
    private  long touchTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 两次返回键，退出程序
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                UiUtil.showLongToast(getApplicationContext(), "再按一次退出程序");
                touchTime = currentTime;
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid()); //获取PID
                System.exit(0);   //常规java、c#的标准退出法，返回值为0代表正常退出
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTabChanged(String tabId) {
        //个人中心界面的判定
          if("mine".equals(tabId)){
              if(!RcUtil.isLogin(this)){
                  //TODO
                  UiUtil.showLongToast(this,"未登录，请先进行登录");
                 // setCurrentTab(0);
                 // RcUtil.tranUi(this,LoginActivity.class);
              }
          }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
