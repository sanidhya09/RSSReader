package com.knn.rssreader.view;

import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * Created by root on 20/1/15.
 */
public class ShapeDialogFactory {

    public static ShapeDrawable getRoundedShapeDrawable(float[] outerRadii, RectF inset, float[] innerRadii, Paint.Style style, int strokeWidth, boolean antiAlias, int bgColor) {
        RectShape rectShape = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(rectShape);
        shapeDrawable.getPaint().setStyle(style);
        shapeDrawable.getPaint().setStrokeWidth(strokeWidth);
        shapeDrawable.getPaint().setAntiAlias(antiAlias);
        shapeDrawable.getPaint().setColor(bgColor);
        return shapeDrawable;
    }





}
