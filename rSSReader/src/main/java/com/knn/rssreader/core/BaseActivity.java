package com.knn.rssreader.core;

import com.knn.rssreader.network.NetworkStateReceiver.NetworkStateChanged;
import com.knn.rssreader.utility.BusProvider;
import com.squareup.otto.Subscribe;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class BaseActivity extends ActionBarActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		BusProvider.getInstance().register(this);
	}

	@Subscribe
	public void onNetworkStateChanged(NetworkStateChanged event)
	{
		if (!event.isInternetConnected())
		{
			Toast.makeText(BaseActivity.this, "No Internet connection!", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		BusProvider.getInstance().unregister(this);
	}
}
