package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.IViewDrawableCreator;
import com.dailyyoga.ui.R;
import com.dailyyoga.ui.drawable.DrawableSize;
import com.dailyyoga.ui.drawable.GradientDrawableCreator;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeCheckBox extends AppCompatCheckBox implements IViewDrawableCreator {

    private GradientDrawableCreator mDrawableCreator;

    public AttributeCheckBox(Context context) {
        this(context, null);
    }

    public AttributeCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeCheckBox);
        AttributeCompat.setCompoundDrawablesTint(typedArray, this);
        AttributeCompat.setViewAttribute(typedArray, this);
    }

    public void setCompoundDrawablesColors(int left, int top, int right, int bottom) {
        if (left != 0) {
            AttributeCompat.setTint(getCompoundDrawables()[0], left);
        }
        if (top != 0) {
            AttributeCompat.setTint(getCompoundDrawables()[1], top);
        }
        if (right != 0) {
            AttributeCompat.setTint(getCompoundDrawables()[2], right);
        }
        if (bottom != 0) {
            AttributeCompat.setTint(getCompoundDrawables()[3], bottom);
        }
    }

    public void setCompoundDrawablesSizes(DrawableSize left, DrawableSize top, DrawableSize right, DrawableSize bottom) {
        if (left != null) {
            left.mergeDrawableSize(getCompoundDrawables()[0]);
        }
        if (top != null) {
            top.mergeDrawableSize(getCompoundDrawables()[1]);
        }
        if (right != null) {
            right.mergeDrawableSize(getCompoundDrawables()[2]);
        }
        if (bottom != null) {
            bottom.mergeDrawableSize(getCompoundDrawables()[3]);
        }
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], getCompoundDrawables()[2], getCompoundDrawables()[3]);
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
