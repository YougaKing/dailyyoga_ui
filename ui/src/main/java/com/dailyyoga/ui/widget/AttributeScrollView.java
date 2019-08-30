package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.R;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeScrollView extends ScrollView {

    public AttributeScrollView(Context context) {
        this(context, null);
    }

    public AttributeScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeScrollView);
        AttributeCompat.setViewAttribute(typedArray, this);
    }
}
