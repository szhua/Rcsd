package com.ecloud.rcsd.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.runer.liabary.util.Arith;

/**
 * Rcsd
 * Create   2017/5/12 15:56;
 * https://github.com/szhua
 *
 * @author sz.hua
 */
public class TouTiaoImageView extends ImageView {
    public TouTiaoImageView(Context context) {
        super(context);
    }

    public TouTiaoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouTiaoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft()
                - getPaddingRight();
        int  height = (int) (Arith.mul(width,0.625) + 0.5f);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
