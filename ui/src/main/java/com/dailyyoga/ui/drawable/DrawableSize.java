package com.dailyyoga.ui.drawable;

import android.graphics.drawable.Drawable;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/9/3 16:44
 * @description:
 */
public class DrawableSize {

    public float width;
    public float height;

    public void mergeDrawableSize(Drawable drawable) {
        if (drawable == null) return;
        if (width == 0) width = drawable.getIntrinsicWidth();
        if (height == 0) height = drawable.getIntrinsicHeight();

        drawable.setBounds(0, 0, (int) width, (int) height);
    }
}
