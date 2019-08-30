package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.R;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeImageButton extends AppCompatImageButton {


    private int imageDrawableColor;

    public AttributeImageButton(Context context) {
        this(context, null);
    }

    public AttributeImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeImageButton);
        imageDrawableColor = AttributeCompat.setImageDrawableTint(typedArray, this);
        AttributeCompat.setViewAttribute(typedArray, this);
    }

    public void setImageDrawableColor(int imageDrawableColor) {
        this.imageDrawableColor = imageDrawableColor;
        AttributeCompat.setTint(getDrawable(), imageDrawableColor);
    }
}
