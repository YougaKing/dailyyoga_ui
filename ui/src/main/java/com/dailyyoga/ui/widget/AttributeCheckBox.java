package com.dailyyoga.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

import com.dailyyoga.ui.AttributeCompat;
import com.dailyyoga.ui.R;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 17:37
 * @description:
 */
public class AttributeCheckBox extends AppCompatCheckBox {

    private int[] compoundDrawablesColors;

    public AttributeCheckBox(Context context) {
        this(context, null);
    }

    public AttributeCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeCheckBox);
        compoundDrawablesColors = AttributeCompat.setCompoundDrawablesTint(typedArray, this);
        AttributeCompat.setViewAttribute(typedArray, this);
    }

    public void setCompoundDrawablesColors(int left, int top, int right, int bottom) {
        if (compoundDrawablesColors == null || compoundDrawablesColors.length != 4) return;
        if (left != 0) {
            compoundDrawablesColors[0] = left;
        }
        if (top != 0) {
            compoundDrawablesColors[1] = top;
        }
        if (right != 0) {
            compoundDrawablesColors[2] = right;
        }
        if (bottom != 0) {
            compoundDrawablesColors[3] = bottom;
        }
        AttributeCompat.setTint(getCompoundDrawables(), compoundDrawablesColors);
    }
}
