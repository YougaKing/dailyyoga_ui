package com.dailyyoga.ui.drawable;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

import com.dailyyoga.ui.R;

import java.lang.reflect.Field;

import static android.graphics.drawable.GradientDrawable.LINEAR_GRADIENT;

/**
 * @author: YougaKingWu@gmail.com
 * @created on: 2019/8/19 16:28
 * @description:
 */
public class GradientDrawableCreator implements ICreateDrawable {
    private int[] states;

    private int shape;
    private ColorStateList solidColor;
    private float cornersRadius;
    private float[] cornerRadii = new float[8];

    private boolean hasCenter;
    private float centerX;
    private float centerY;

    private int gradientType = LINEAR_GRADIENT;
    private int[] gradientColors;
    private float gradientRadius = 0;
    private Orientation orientation;

    private boolean useLevel;

    private boolean hasSize;
    private float sizeWidth;
    private float sizeHeight;

    private float strokeWidth = -1;
    private ColorStateList strokeColor;
    private float strokeDashWidth;
    private float strokeGap;

    private Rect padding = new Rect();


    GradientDrawableCreator(TypedArray typedArray) {
        states = new int[]{
                android.R.attr.state_enabled, -android.R.attr.state_enabled,
                android.R.attr.state_focused, -android.R.attr.state_focused,
                android.R.attr.state_checked, -android.R.attr.state_checked,
                android.R.attr.state_selected, -android.R.attr.state_selected,
                android.R.attr.state_pressed, -android.R.attr.state_pressed,
        };

        parseTypedArray(typedArray);
    }

    private void parseTypedArray(TypedArray typedArray) {

        int centerColor = 0;
        int endColor = 0;
        int startColor = 0;

        int gradientAngle = 0;

        shape = typedArray.getInt(R.styleable.AttributeView_attr_shape, 0);
        solidColor = typedArray.getColorStateList(R.styleable.AttributeView_attr_solid_color);

        cornersRadius = typedArray.getDimension(R.styleable.AttributeView_attr_corners_radius, 0);
        cornerRadii[6] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_bottomLeftRadius, 0);
        cornerRadii[7] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_bottomLeftRadius, 0);
        cornerRadii[4] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_bottomRightRadius, 0);
        cornerRadii[5] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_bottomRightRadius, 0);
        cornerRadii[0] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_topLeftRadius, 0);
        cornerRadii[1] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_topLeftRadius, 0);
        cornerRadii[2] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_topRightRadius, 0);
        cornerRadii[3] = typedArray.getDimension(R.styleable.AttributeView_attr_corners_topRightRadius, 0);

        centerX = typedArray.getFloat(R.styleable.AttributeView_attr_gradient_centerX, -1);
        centerY = typedArray.getFloat(R.styleable.AttributeView_attr_gradient_centerY, -1);
        centerColor = typedArray.getColor(R.styleable.AttributeView_attr_gradient_centerColor, 0);
        endColor = typedArray.getColor(R.styleable.AttributeView_attr_gradient_endColor, 0);
        startColor = typedArray.getColor(R.styleable.AttributeView_attr_gradient_startColor, 0);

        gradientAngle = typedArray.getInteger(R.styleable.AttributeView_attr_gradient_angle, 0);
        gradientRadius = typedArray.getDimension(R.styleable.AttributeView_attr_gradient_gradientRadius, 0);
        gradientType = typedArray.getInt(R.styleable.AttributeView_attr_gradient_type, 0);
        useLevel = typedArray.getBoolean(R.styleable.AttributeView_attr_gradient_useLevel, false);

        padding.left = (int) typedArray.getDimension(R.styleable.AttributeView_attr_padding_left, 0);
        padding.top = (int) typedArray.getDimension(R.styleable.AttributeView_attr_padding_top, 0);
        padding.right = (int) typedArray.getDimension(R.styleable.AttributeView_attr_padding_right, 0);
        padding.bottom = (int) typedArray.getDimension(R.styleable.AttributeView_attr_padding_bottom, 0);

        sizeWidth = typedArray.getDimension(R.styleable.AttributeView_attr_size_width, 0);
        sizeHeight = typedArray.getDimension(R.styleable.AttributeView_attr_size_height, 0);
        strokeWidth = typedArray.getDimension(R.styleable.AttributeView_attr_stroke_width, 0);
        strokeColor = typedArray.getColorStateList(R.styleable.AttributeView_attr_stroke_color);
        strokeDashWidth = typedArray.getDimension(R.styleable.AttributeView_attr_stroke_dashWidth, 0);
        strokeGap = typedArray.getDimension(R.styleable.AttributeView_attr_stroke_dashGap, 0);

        if (typedArray.hasValue(R.styleable.AttributeView_attr_gradient_startColor) &&
                typedArray.hasValue(R.styleable.AttributeView_attr_gradient_endColor) &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

            if (typedArray.hasValue(R.styleable.AttributeView_attr_gradient_centerColor)) {
                gradientColors = new int[3];
                gradientColors[0] = startColor;
                gradientColors[1] = centerColor;
                gradientColors[2] = endColor;
            } else {
                gradientColors = new int[2];
                gradientColors[0] = startColor;
                gradientColors[1] = endColor;
            }
        }

        hasSize = typedArray.hasValue(R.styleable.AttributeView_attr_size_width) &&
                typedArray.hasValue(R.styleable.AttributeView_attr_size_height);

        hasCenter = typedArray.hasValue(R.styleable.AttributeView_attr_gradient_centerX) &&
                typedArray.hasValue(R.styleable.AttributeView_attr_gradient_centerY);

        if (gradientType == LINEAR_GRADIENT &&
                typedArray.hasValue(R.styleable.AttributeView_attr_gradient_angle) &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            gradientAngle %= 360;

            orientation = Orientation.LEFT_RIGHT;
            switch (gradientAngle) {
                case 0:
                    orientation = Orientation.LEFT_RIGHT;
                    break;
                case 45:
                    orientation = Orientation.BL_TR;
                    break;
                case 90:
                    orientation = Orientation.BOTTOM_TOP;
                    break;
                case 135:
                    orientation = Orientation.BR_TL;
                    break;
                case 180:
                    orientation = Orientation.RIGHT_LEFT;
                    break;
                case 225:
                    orientation = Orientation.TR_BL;
                    break;
                case 270:
                    orientation = Orientation.TOP_BOTTOM;
                    break;
                case 315:
                    orientation = Orientation.TL_BR;
                    break;
            }
        }

        if (!typedArray.hasValue(R.styleable.AttributeView_attr_padding_left) ||
                !typedArray.hasValue(R.styleable.AttributeView_attr_padding_top) ||
                !typedArray.hasValue(R.styleable.AttributeView_attr_padding_right) ||
                !typedArray.hasValue(R.styleable.AttributeView_attr_padding_bottom)) {
            padding = null;
        }
    }

    public GradientDrawable loadDrawable() {
        GradientDrawable drawable = new GradientDrawable();

        drawable.setShape(shape);
        drawable.setCornerRadius(cornersRadius);

        if (hasSetRadius(cornerRadii)) {
            drawable.setCornerRadii(cornerRadii);
        }
        if (hasSize) {
            drawable.setSize((int) sizeWidth, (int) sizeHeight);
        }

        //设置填充颜色
        if (solidColor != null && !solidColor.isStateful()) {
            drawable.setColor(solidColor.getDefaultColor());
        }
        //设置边框颜色

        if (strokeWidth > 0 && strokeColor != null && !strokeColor.isStateful()) {
            drawable.setStroke((int) strokeWidth, strokeColor.getDefaultColor(), strokeDashWidth, strokeGap);
        }

        if (hasCenter) {
            drawable.setGradientCenter(centerX, centerY);
        }

        if (gradientColors != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setColors(gradientColors);
        }

        drawable.setGradientRadius(gradientRadius);
        drawable.setGradientType(gradientType);
        if (orientation != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            drawable.setOrientation(orientation);
        }

        drawable.setUseLevel(useLevel);

        if (padding != null) {
            try {
                Field paddingField = drawable.getClass().getDeclaredField("mPadding");
                paddingField.setAccessible(true);
                paddingField.set(drawable, padding);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return drawable;
    }

    @Override
    public Drawable create() {

        GradientDrawable drawable = loadDrawable();

        if (!isStateful()) return drawable;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (solidColor != null) {
                drawable.setColor(solidColor);
            }
            if (strokeWidth > 0 && strokeColor != null) {
                drawable.setStroke((int) strokeWidth, strokeColor, strokeDashWidth, strokeGap);
            }
            return drawable;
        }

        StateListDrawable listDrawable = new StateListDrawable();
        int[] stateSet;
        int color;

        GradientDrawable stateDrawable;
        for (int state : states) {
            stateSet = new int[]{state};

            stateDrawable = loadDrawable();

            if (solidColor != null) {
                color = solidColor.getColorForState(stateSet, solidColor.getDefaultColor());
                stateDrawable.setColor(color);
            }
            if (strokeWidth > 0 && strokeColor != null) {
                color = strokeColor.getColorForState(stateSet, strokeColor.getDefaultColor());
                stateDrawable.setStroke((int) strokeWidth, color, strokeDashWidth, strokeGap);
            }
            listDrawable.addState(stateSet, drawable);
        }

        return listDrawable;
    }

    public float getCornersRadius() {
        return cornersRadius;
    }

    private boolean isStateful() {
        if (solidColor == null && strokeColor == null) return false;

        int fulSize = 0;
        if (solidColor != null && solidColor.isStateful()) {
            fulSize++;
        }
        if (strokeColor != null && strokeColor.isStateful()) {
            fulSize++;
        }
        if (fulSize == 0) return false;

        return true;
    }

    private boolean hasSetRadius(float[] radius) {
        boolean hasSet = false;
        for (float f : radius) {
            if (f != 0.0f) {
                hasSet = true;
                break;
            }
        }
        return hasSet;
    }
}
