package com.knn.rssreader.utility;

import android.graphics.Color;

/**
 * Created by root on 20/1/15.
 */
public class GradientFactory {


    public static android.graphics.drawable.GradientDrawable getGradientDrawable() {
        android.graphics.drawable.GradientDrawable gradientDrawable = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.BOTTOM_TOP, new int[]{Color.WHITE, Color.WHITE});
        //gradientDrawable.setStroke(10, Color.BLUE);
        return gradientDrawable;
    }

}
