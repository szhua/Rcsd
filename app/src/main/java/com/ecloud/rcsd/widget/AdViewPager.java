package com.ecloud.rcsd.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ecloud.rcsd.R;
import com.ecloud.rcsd.adapter.BannerAdapter;
import com.runer.liabary.indicator.CircleIndicator;
import com.runer.liabary.util.Arith;
import com.umeng.socialize.utils.Log;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class AdViewPager extends LinearLayout {


	private ViewPager viewPager ;
	private CircleIndicator indicator;

	private int currentItem = 0;
	private BannerAdapter bannerAdapter ;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			try {
				viewPager.setCurrentItem(currentItem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	private Timer timer;
	private class ScrollTask extends TimerTask {
		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) %4;
				handler.obtainMessage().sendToTarget();
			}
		}
	}

	public void setIndicatorGone(){
		if(indicator!=null){
			indicator.setVisibility(View.GONE);
		}
	}

	public View getViewPager(){
		return viewPager ;
	}

	double ratio ;
	public void setViewPagerHeight(final double ratio){
		this.ratio =ratio ;
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		double windowWidth = wm.getDefaultDisplay().getWidth();
		double viewPageHeight =Arith.mul(windowWidth, ratio);
		bannerAdapter.setRatio(ratio);
		postInvalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(ratio!=0){
			int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
					- getPaddingRight();
			int height = (int) (Arith.mul(width, ratio) + 0.5f);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
					MeasureSpec.EXACTLY);		
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	
	

	public void setBannerEntities() {
		if(bannerAdapter!=null){
			viewPager.setAdapter(bannerAdapter);
			indicator.setViewPager(viewPager);

			if(timer==null){
				timer=new Timer() ;
			}else{
				timer.cancel();
				timer =null ;
				timer =new Timer();
			}
			timer.schedule(new ScrollTask(),0,3000);
		}
	}

	public AdViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.ad_viewpager_layout, this) ;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		viewPager =(ViewPager) findViewById(R.id.viewpager);
		indicator =(CircleIndicator) findViewById(R.id.indicator);

		bannerAdapter =new BannerAdapter((Activity)getContext());
		viewPager.setAdapter(bannerAdapter);
		indicator.setViewPager(viewPager);
		viewPager.addOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				currentItem =arg0;
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

	}




}
