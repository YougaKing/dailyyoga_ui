package com.dailyyoga.ui.drawable;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 16:14
 * @description:
 */
public class DrawableFactory {

    //获取shape属性的drawable
    public static Drawable getDrawable(TypedArray typedArray) {
        return new GradientDrawableCreator(typedArray).create();
    }
}
