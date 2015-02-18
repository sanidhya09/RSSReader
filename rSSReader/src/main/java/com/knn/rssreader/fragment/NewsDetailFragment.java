package com.knn.rssreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.knn.rssreader.adapter.NewsHeadlinesAdapter;
import com.knn.rssreader.core.MyApplication;
import com.knn.rssreader.utility.BusProvider;
import com.squareup.otto.Subscribe;

public class NewsDetailFragment extends Fragment
{

	protected MyApplication myApplication;
	private FragmentActivity activity;
	private NewsDetailFragment context;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		activity = getActivity();
		context = NewsDetailFragment.this;
		myApplication = (MyApplication) activity.getApplication();
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
	{
		return super.onCreateView(inflater, container, savedInstanceState);
		
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		BusProvider.getInstance().register(context);
	}
	
	@Subscribe
	public void getNewsHeadLines(NewsHeadlinesAdapter newsHeadlinesAdapter)
	{
		Toast.makeText(activity, "Event handled", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onStop()
	{
		BusProvider.getInstance().unregister(context);
		super.onStop();
		
	}

}
