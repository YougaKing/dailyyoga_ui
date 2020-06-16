package com.dailyyoga.ui;

import com.dailyyoga.ui.drawable.GradientDrawableCreator;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2020/6/16 16:20
 * @description:
 */
public interface IViewDrawableCreator {

    void setDrawableCreator(GradientDrawableCreator drawableCreator);

    GradientDrawableCreator getDrawableCreator();
}
