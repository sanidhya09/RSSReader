package com.knn.rssreader.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.knn.rssreader.R;
import com.knn.rssreader.view.MyDialog;
import com.knn.rssreader.view.ShapeDialogFactory;

/**
 * Created by root on 19/1/15.
 */
public class DialogFactory {


    public static void showPaymentDialogOption(Activity context) {

        MyDialog myDialog = new MyDialog(context, R.style.dialog_theme);
        int screenHeight = Utility.getScreenHeight(context);

        ShapeDrawable roundedShapeDrawable = ShapeDialogFactory.getRoundedShapeDrawable(new float[]{5, 5, 5, 5, 5, 5, 5, 5}, null, null, Paint.Style.STROKE, 2, true, Color.WHITE);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(screenHeight / 2, screenHeight / 2);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, screenHeight / 10);


        myDialog.setContentView(linearLayout, lp);


        TextView headingTv = new TextView(context);
        headingTv.setLayoutParams(lp1);
        headingTv.setGravity(Gravity.CENTER);
        headingTv.setText("Select Payment Method");
        headingTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, context.getResources().getDimension(R.dimen.txt_heading_7));


        TextView tv = new TextView(context);
        tv.setLayoutParams(lp1);
        tv.setGravity(Gravity.CENTER);
        tv.setText("Credit");
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, context.getResources().getDimension(R.dimen.txt_heading_6));

        TextView tv1 = new TextView(context);
        tv1.setLayoutParams(lp1);
        tv1.setGravity(Gravity.CENTER);
        tv1.setText("Debit");
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, context.getResources().getDimension(R.dimen.txt_heading_6));

        TextView tv2 = new TextView(context);
        tv2.setLayoutParams(lp1);
        tv2.setGravity(Gravity.CENTER);
        tv2.setText("Ach");
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, context.getResources().getDimension(R.dimen.txt_heading_6));

        linearLayout.addView(headingTv);
        linearLayout.addView(tv);
        linearLayout.addView(tv1);
        linearLayout.addView(tv2);
        Utility.setDrawable(context, linearLayout, GradientFactory.getGradientDrawable());
        myDialog.getWindow().setBackgroundDrawable(roundedShapeDrawable);
        myDialog.show();


    }


    public static void showOkDialog(final Activity context, final String msg) {
        Runnable okay = new Runnable() {

            @Override
            public void run() {
                Dialog dialog;
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(msg);
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });


                dialog = builder.create();
                dialog.show();
            }
        };
        Utility.runOnMainThread(context, okay);


    }


}
