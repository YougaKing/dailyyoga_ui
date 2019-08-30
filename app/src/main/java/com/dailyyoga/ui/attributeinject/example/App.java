package com.dailyyoga.ui.attributeinject.example;

import android.app.Application;

import com.dailyyoga.ui.AttributeInject;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 16:49
 * @description:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AttributeInject.inject(this);
    }
}
