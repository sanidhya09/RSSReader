package com.knn.rssreader.network;

import java.io.IOException;
import java.io.InputStream;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.util.Log;

import com.knn.rssreader.utility.Constants;
import com.knn.rssreader.utility.DialogFactory;
import com.knn.rssreader.utility.Utility;

/**
 * Created by root on 9/1/15.
 */
public class NetworkErrorHandler {

    private static final String TAG = NetworkErrorHandler.class.getSimpleName();
    private Activity context;

    public NetworkErrorHandler(Activity context) {
        this.context = context;
    }


    public Throwable handleError(RetrofitError cause) {

        if (cause != null) {
            Response response = cause.getResponse();
            try {
                InputStream in = response.getBody().in();
                String s = Utility.readTextFromInputStream(in);
                Log.d(TAG, " Retrofit Error res is: " + s);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String message = cause.getMessage();
            Log.d(TAG, " Retrofit Error is: " + message);


            if (response != null) {
                int status = response.getStatus();

                switch (status) {
                    case 500:
                        DialogFactory.showOkDialog(context, Constants.ToastMessage.NetworkError.RESPONSE_CODE_500);
                        break;


                }

            }
        }

        return cause;
    }


}
