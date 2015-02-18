package com.knn.rssreader.view;
import com.knn.rssreader.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;



/**
 * Created by root on 19/1/15.
 */
public class ProgressHudDialog extends Dialog {

    public ProgressHudDialog(Context context) {
        super(context);
    }

    public ProgressHudDialog(Context context, int theme) {
        super(context, theme);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        if (imageView != null) {
            AnimationDrawable spinner = (AnimationDrawable) imageView
                    .getBackground();
            spinner.start();
        }
    }






}
