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
import com.dailyyoga.ui.drawable.DrawableSize;
import com.dailyyoga.ui.drawable.GradientDrawableCreator;

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
            GradientDrawableCreator drawableCreator = DrawableFactory.getDrawable(typedArray);
            if (view instanceof IViewDrawableCreator) {
                ((IViewDrawableCreator) view).setDrawableCreator(drawableCreator);
            }
            setBackground(drawableCreator.create(), view, typedArray);

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return view;
        } finally {
            typedArray.recycle();
        }
    }

    public static void setCompoundDrawablesTint(TypedArray typedArray, View view) {

        Drawable[] compoundDrawables = null;
        if (view instanceof TextView) {
            compoundDrawables = ((TextView) view).getCompoundDrawables();
        }
        if (compoundDrawables == null) return;

        int[] compoundDrawablesColors = new int[4];
        compoundDrawablesColors[0] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_left_tint, 0);
        compoundDrawablesColors[1] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_top_tint, 0);
        compoundDrawablesColors[2] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_right_tint, 0);
        compoundDrawablesColors[3] = typedArray.getColor(R.styleable.AttributeView_attr_drawable_bottom_tint, 0);

        DrawableSize[] compoundDrawablesSizes = new DrawableSize[4];
        compoundDrawablesSizes[0] = new DrawableSize();
        compoundDrawablesSizes[0].width = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_left_width, 0);
        compoundDrawablesSizes[0].height = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_left_height, 0);
        compoundDrawablesSizes[1] = new DrawableSize();
        compoundDrawablesSizes[1].width = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_top_width, 0);
        compoundDrawablesSizes[1].height = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_top_height, 0);
        compoundDrawablesSizes[2] = new DrawableSize();
        compoundDrawablesSizes[2].width = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_right_width, 0);
        compoundDrawablesSizes[2].height = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_right_height, 0);
        compoundDrawablesSizes[3] = new DrawableSize();
        compoundDrawablesSizes[3].width = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_bottom_width, 0);
        compoundDrawablesSizes[3].height = typedArray.getDimension(R.styleable.AttributeView_attr_drawable_bottom_height, 0);

        mergeDrawableSize(compoundDrawablesSizes, compoundDrawables);
        ((TextView) view).setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);

        setTint(compoundDrawables, compoundDrawablesColors);
    }

    private static void mergeDrawableSize(DrawableSize[] drawablesSizes, Drawable[] drawables) {
        if (drawablesSizes == null || drawables == null) return;
        if (drawablesSizes.length != drawables.length) return;

        for (int i = 0; i < drawablesSizes.length; i++) {
            DrawableSize size = drawablesSizes[i];
            Drawable drawable = drawables[i];
            if (size == null || drawable == null) continue;
            size.mergeDrawableSize(drawable);
        }
    }


    public static void setImageDrawableTint(TypedArray typedArray, View view) {
        int imageDrawableColor;
        Drawable imageDrawable = null;
        if (view instanceof ImageView) {
            imageDrawable = ((ImageView) view).getDrawable();
        }
        if (imageDrawable == null) return;
        imageDrawableColor = typedArray.getColor(R.styleable.AttributeView_attr_src_tint, 0);
        setTint(imageDrawable, imageDrawableColor);
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
