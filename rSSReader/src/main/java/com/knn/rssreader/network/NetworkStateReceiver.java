package com.knn.rssreader.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.knn.rssreader.utility.BusProvider;

public class NetworkStateReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		 if(intent.getExtras()!=null) {
			 BusProvider.getInstance().register(this);
			 if(isOnline(context)){
				 BusProvider.getInstance().post(new NetworkStateChanged(true));
				 
			 }else{
				 BusProvider.getInstance().post(new NetworkStateChanged(false));
			 }

		 }
	}
	public class NetworkStateChanged {

	    private boolean mIsInternetConnected;

	    public NetworkStateChanged(boolean isInternetConnected) {
	        this.mIsInternetConnected = isInternetConnected;
	    }

	    public boolean isInternetConnected() {
	        return this.mIsInternetConnected;
	    }
	}
	public boolean isOnline(Context context) {

	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    //should check null because in air plan mode it will be null
	    return (netInfo != null && netInfo.isConnected());

	}
}

