package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.IViewDrawableCreator;
import com.dailyyoga.ui.R;
import com.dailyyoga.ui.drawable.GradientDrawableCreator;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/30 9:04
 * @description:
 */
public class AttributeImageView extends AppCompatImageView implements IViewDrawableCreator {

    private GradientDrawableCreator mDrawableCreator;

    public AttributeImageView(Context context) {
        this(context, null);
    }

    public AttributeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeImageView);
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
