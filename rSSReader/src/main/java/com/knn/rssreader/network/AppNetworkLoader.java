package com.knn.rssreader.network;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.knn.rssreader.R;
import com.knn.rssreader.core.MyApplication;
import com.knn.rssreader.view.ProgressHudDialog;

/**
 * Created by root on 9/1/15.
 */
public class AppNetworkLoader {

    private static AppNetworkLoader appNetworkLoader;
    private static ProgressHudDialog dialog;
    private MyApplication myApplication = null;


    public static AppNetworkLoader getInstance(MyApplication myApplication) {
        //if (appNetworkLoader == null || dialog==null || !dialog.getContext().equals(context)) {
        if (appNetworkLoader == null) {
            appNetworkLoader = new AppNetworkLoader(myApplication);
        }
        // }
        return appNetworkLoader;
    }

    public static AppNetworkLoader getInstance() {
        return appNetworkLoader;
    }

    private AppNetworkLoader(MyApplication myApplication) {
        this.myApplication = myApplication;
    }


    private void createAppNetworkLoader(Context mContext) {
        dialog = new ProgressHudDialog(mContext, R.style.ProgressHUD);

        dialog.setTitle("");
        dialog.setContentView(R.layout.dialog_loader);
        dialog.findViewById(R.id.progress_hud_id).setAlpha(.9f);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        //dialog.findViewById(R.id.spinnerImageView).start
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.2f;
        dialog.getWindow().setAttributes(lp);
        // dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

    }

    public void showLoader(String message) {

        createAppNetworkLoader(myApplication.getCurrentActivity());


        if (message == null || message.length() == 0) {
            dialog.findViewById(R.id.message).setVisibility(View.GONE);
        } else {
            TextView txt = (TextView) dialog.findViewById(R.id.message);
            txt.setText(message);
        }
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();

        }
    }


    public void cancelLoader(Activity activity) {
        if (dialog == null)
            return;


        if (dialog.isShowing()) {
            dialog.cancel();

        }
    }

}
