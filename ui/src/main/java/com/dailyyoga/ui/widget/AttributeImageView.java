package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.R;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/30 9:04
 * @description:
 */
public class AttributeImageView extends AppCompatImageView {

    private int imageDrawableColor;

    public AttributeImageView(Context context) {
        this(context, null);
    }

    public AttributeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeImageView);
        imageDrawableColor = AttributeCompat.setImageDrawableTint(typedArray, this);
        AttributeCompat.setViewAttribute(typedArray, this);
    }

    public void setImageDrawableColor(int imageDrawableColor) {
        this.imageDrawableColor = imageDrawableColor;
        AttributeCompat.setTint(getDrawable(), imageDrawableColor);
    }
}