package com.dailyyoga.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;

import java.lang.reflect.Field;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 15:03
 * @description:
 */
public class AttributeInject {

    private static final String IGNORE_VIEW = "com.dailyyoga.ui.widget";
    private static final String TAG = AttributeInject.class.getName();

    public static void inject(Application app) {
        if (app == null) return;
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                injectViewFactory(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private static LayoutInflater injectViewFactory(Activity activity) {
        LayoutInflater inflater = activity.getLayoutInflater();

        if (inflater.getFactory2() == null) {
            AttributeFactory factory = getFactory();
            if (activity instanceof AppCompatActivity) {
                final AppCompatDelegate delegate = ((AppCompatActivity) activity).getDelegate();
                factory.setFactory(new Factory() {
                    @Override
                    public View onCreateView(String name, Context context, AttributeSet attrs) {
                        return delegate.createView(null, name, context, attrs);
                    }
                });
            }
            inflater.setFactory2(factory);
        } else if (!(inflater.getFactory2() instanceof AttributeFactory)) {
//            Log.i(TAG, "The Activity's LayoutInflater already has a Factory installed so we can not install AttributeFactory");
            return forceInjectViewFactory(inflater);
        }
        return inflater;
    }

    private static LayoutInflater forceInjectViewFactory(final LayoutInflater inflater) {
        try {
            final Factory2 originFactory2 = inflater.getFactory2();
            AttributeFactory factory = getFactory();
            factory.setFactory(new Factory() {
                @Override
                public View onCreateView(String name, Context context, AttributeSet attrs) {
                    return originFactory2.onCreateView(name, context, attrs);
                }
            });

            Field mFactory2 = LayoutInflater.class.getDeclaredField("mFactory2");
            mFactory2.setAccessible(true);
            mFactory2.set(inflater, factory);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflater;
    }


    @NonNull
    private static AttributeFactory getFactory() {
        return new AttributeFactory();
    }

    public static class AttributeFactory implements Factory2 {

        private Factory mFactory;

        /**
         * From {@link Factory2}.
         */
        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return createView(parent, name, context, attrs);
        }

        /**
         * From {@link Factory2}.
         */
        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return onCreateView(null, name, context, attrs);
        }


        public View createView(View parent, final String name, @NonNull Context context, @NonNull AttributeSet attrs) {
            View view = null;

            if (mFactory != null) {
                view = mFactory.onCreateView(name, context, attrs);
            }

            //如果是已包含属性View，代表已经进行了属性设置，无需再次创建，留给系统创建就行
            if (name.startsWith(IGNORE_VIEW)) {
                return view;
            }

            return AttributeInflater.createView(parent, name, context, attrs, view);
        }

        public void setFactory(Factory factory) {
            mFactory = factory;
        }
    }

}
