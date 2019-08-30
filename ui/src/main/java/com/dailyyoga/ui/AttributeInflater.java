package com.dailyyoga.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import com.dailyyoga.ui.widget.AttributeButton;
import com.dailyyoga.ui.widget.AttributeCheckBox;
import com.dailyyoga.ui.widget.AttributeEditText;
import com.dailyyoga.ui.widget.AttributeImageButton;
import com.dailyyoga.ui.widget.AttributeImageView;
import com.dailyyoga.ui.widget.AttributeTextView;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/29 10:32
 * @description:
 */
public class AttributeInflater {


    public static View createView(View parent, final String name, @NonNull Context context, @NonNull AttributeSet attrs, View view) {
        if (view == null) return null;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeView);
        AttributeCompat.setViewAttribute(typedArray, view);
        return view;
    }

}
