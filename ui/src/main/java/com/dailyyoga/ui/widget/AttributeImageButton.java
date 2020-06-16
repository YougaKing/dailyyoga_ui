package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageButton;
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
public class AttributeImageButton extends AppCompatImageButton implements IViewDrawableCreator {

    private GradientDrawableCreator mDrawableCreator;

    public AttributeImageButton(Context context) {
        this(context, null);
    }

    public AttributeImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeImageButton);
        AttributeCompat.setImageDrawableTint(typedArray, this);
        AttributeCompat.setViewAttribute(typedArray, this);
    }

    public void setImageDrawableColor(int imageDrawableColor) {
        AttributeCompat.setTint(getDrawable(), imageDrawableColor);
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
