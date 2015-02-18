package com.knn.rssreader.network;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.knn.rssreader.core.MyApplication;
import com.knn.rssreader.utility.BusProvider;
import com.knn.rssreader.utility.Constants.SERVICE_MODE;

public class MyEventCallBack<T> implements Callback<T>
{

	private boolean isDismiss;
	private MyApplication myApplication;
	private SERVICE_MODE sMode;

	public MyEventCallBack(MyApplication myApplication, SERVICE_MODE sMode, boolean isDismiss,boolean isProgress, String message)
	{
		this.isDismiss = isDismiss;
		this.myApplication = myApplication;
		this.sMode = sMode;
		if (isProgress)
		{
			AppNetworkLoader.getInstance(myApplication).showLoader(message);
		}
	}

	@Override
	public void success(T t, Response response)
	{
		checkProgressStatus();
		BusProvider.getInstance().post(new MyResponse<T>(t, sMode));
	}

	@Override
	public void failure(RetrofitError error)
	{
		checkProgressStatus();
		new NetworkErrorHandler(myApplication.getCurrentActivity()).handleError(error);

	}

	private void checkProgressStatus()
	{
		if (isDismiss && AppNetworkLoader.getInstance() != null)
		{
			AppNetworkLoader.getInstance().cancelLoader(myApplication.getCurrentActivity());
		}
	}

}
