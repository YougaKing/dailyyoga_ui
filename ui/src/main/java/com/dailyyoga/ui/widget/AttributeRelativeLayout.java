package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.R;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeRelativeLayout extends RelativeLayout {

    public AttributeRelativeLayout(Context context) {
        this(context, null);
    }

    public AttributeRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeRelativeLayout);
        AttributeCompat.setViewAttribute(typedArray, this);
    }
}
