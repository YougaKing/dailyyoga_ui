package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.IViewDrawableCreator;
import com.dailyyoga.ui.R;
import com.dailyyoga.ui.drawable.GradientDrawableCreator;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeConstraintLayout extends ConstraintLayout implements IViewDrawableCreator {

    private GradientDrawableCreator mDrawableCreator;

    public AttributeConstraintLayout(Context context) {
        this(context, null);
    }

    public AttributeConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeConstraintLayout);
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
