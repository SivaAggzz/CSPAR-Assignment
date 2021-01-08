package com.saapps.app21.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.saapps.app21.R;
import com.saapps.app21.databinding.DoubleTiViewBinding;
import com.saapps.app21.helpers.CommonMethods;

public class DoubleTIView extends RelativeLayout {

    private DoubleTiViewBinding tiViewBinding;



    public DoubleTIView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tiViewBinding= DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.double_ti_view, this,true);
        TypedArray attributes = null;

        try {
            attributes = context.obtainStyledAttributes(attrs, R.styleable.DoubleTIView);
            String titleValue = attributes.getString(R.styleable.DoubleTIView_iconPrefTitle);
            String summaryValue = attributes.getString(R.styleable.DoubleTIView_iconPrefSummary);
            int iconValue = attributes.getResourceId(R.styleable.DoubleTIView_iconPref, R.color.colorPrimary);



            tiViewBinding.customTitle.setText(titleValue);
            tiViewBinding.customDesc.setText(summaryValue);
            CommonMethods.loadImage(tiViewBinding.customImage,iconValue);

        } finally {
            assert attributes != null;
            attributes.recycle();
        }


    }


    public void setSummary(String summary) {
        tiViewBinding.customDesc.setText(summary);
    }
}
