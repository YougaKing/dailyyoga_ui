package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.IViewDrawableCreator;
import com.dailyyoga.ui.R;
import com.dailyyoga.ui.drawable.GradientDrawableCreator;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeFrameLayout extends FrameLayout implements IViewDrawableCreator {

    private GradientDrawableCreator mDrawableCreator;

    public AttributeFrameLayout(Context context) {
        this(context, null);
    }

    public AttributeFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeFrameLayout);
        AttributeCompat.setViewAttribute(typedArray, this);
    }

    @Override
    public void setDrawableCreator(GradientDrawableCreator drawableCreator) {
        mDrawableCreator = drawableCreator;
    }

    @Override
    public GradientDrawableCreator getDrawableCreator() {
        return mDrawableCreator;
    }
}
