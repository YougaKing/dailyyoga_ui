package com.dailyyoga.ui;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dailyyoga.ui.drawable.DrawableFactory;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/29 16:13
 * @description:
 */
public class AttributeCompat {

    public static View setViewAttribute(TypedArray typedArray, View view) {
        if (view == null || typedArray == null) return null;
        try {
            if (typedArray.getIndexCount() == 0) {
                return view;
            }
            Drawable drawable = DrawableFactory.getDrawable(typedArray);
            setBackground(drawable, view, typedArray);

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return view;
        } finally {
            typedArray.recycle();
        }
    }

    public static int[] setCompoundDrawablesTint(TypedArray typedArray, View view) {
        int[] compoundDrawablesColors = new int[4];
        Drawable[] compoundDrawables = null;
        if (view instanceof TextView) {
            compoundDrawables = ((TextView) view).getCompoundDrawables();
        }
        if (compoundDrawables == null) return compoundDrawablesColors;
        compoundDrawablesColors[0] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_left_tint, 0);
        compoundDrawablesColors[1] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_top_tint, 0);
        compoundDrawablesColors[2] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_right_tint, 0);
        compoundDrawablesColors[3] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_bottom_tint, 0);
        setTint(compoundDrawables, compoundDrawablesColors);
        return compoundDrawablesColors;
    }

    public static int setImageDrawableTint(TypedArray typedArray, View view) {
        int imageDrawableColor = 0;
        Drawable imageDrawable = null;
        if (view instanceof ImageView) {
            imageDrawable = ((ImageView) view).getDrawable();
        }
        if (imageDrawable == null) return imageDrawableColor;
        imageDrawableColor = typedArray.getColor(R.styleable.AttributeView_attr_src_tint, 0);
        setTint(imageDrawable, imageDrawableColor);
        return imageDrawableColor;
    }

    private static void setBackground(Drawable drawable, View view, TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.AttributeView_attr_stroke_width)
                && typedArray.hasValue(R.styleable.AttributeView_attr_stroke_position)) {
            //bl_stroke_position flag默认值
            int left = 1 << 1;
            int top = 1 << 2;
            int right = 1 << 3;
            int bottom = 1 << 4;
            float width = typedArray.getDimension(R.styleable.AttributeView_attr_stroke_width, 0f);
            int position = typedArray.getInt(R.styleable.AttributeView_attr_stroke_position, 0);
            float leftValue = hasStatus(position, left) ? width : -width;
            float topValue = hasStatus(position, top) ? width : -width;
            float rightValue = hasStatus(position, right) ? width : -width;
            float bottomValue = hasStatus(position, bottom) ? width : -width;
            drawable = new LayerDrawable(new Drawable[]{drawable});
            ((LayerDrawable) drawable).setLayerInset(0, (int) leftValue, (int) topValue, (int) rightValue, (int) bottomValue);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }


    private static boolean hasStatus(int flag, int status) {
        return (flag & status) == status;
    }

    /**
     * Returns drawables for the left, top, right, and bottom borders.
     *
     * @attr ref android.R.styleable#TextView_drawableLeft
     * @attr ref android.R.styleable#TextView_drawableTop
     * @attr ref android.R.styleable#TextView_drawableRight
     * @attr ref android.R.styleable#TextView_drawableBottom
     */
    public static void setTint(Drawable[] drawables, int[] colors) {
        if (drawables == null || colors == null) return;

        Drawable drawable;
        int color;
        for (int i = 0; i < drawables.length; i++) {
            drawable = drawables[i];
            color = colors[i];
            setTint(drawable, color);
        }
    }


    public static void setTint(Drawable drawable, int color) {
        if (drawable == null || color == 0) return;
        drawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(drawable, color);
    }
}
