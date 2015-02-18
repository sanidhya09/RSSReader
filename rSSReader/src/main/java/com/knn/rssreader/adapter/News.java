package com.knn.rssreader.adapter;

import com.google.gson.annotations.Expose;

public class News
	{

		@Expose
		public String newsid;
		@Expose
		public String headline;
		@Expose
		public String thumbnail;
		@Expose
		public String original;
		@Expose
		public String time;

	}