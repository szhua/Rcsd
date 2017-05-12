package com.ecloud.rcsd.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.ecloud.rcsd.R;
import com.ecloud.rcsd.widget.RatioImageView;
import com.umeng.socialize.utils.Log;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Pcdd
 * Create   2017/3/15 13:30;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public final class BannerAdapter extends PagerAdapter  {


	double ratio ;
	public void setRatio(double ratio){
		this.ratio =ratio ;
	}



	private Activity context;

	public BannerAdapter(Activity context){
		this.context =context ;
	}

	@Override
	public int getCount() {
		return 3 ;
	}
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {


		View view =View.inflate(container.getContext(), R.layout.banner_layout, null);
		RatioImageView imageView =(RatioImageView) view.findViewById(R.id.img);
	   //imageView.setRatio(ratio);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		Glide.with(context).load(R.drawable.banner).placeholder(R.drawable.banner).into(imageView);
		container.addView(view);

		return view;
	}



	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
