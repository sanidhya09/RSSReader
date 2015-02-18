package com.knn.rssreader.network;

import com.knn.rssreader.utility.Constants;

public class MyResponse<T>
{
	public T responseFromServer;
	public Constants.SERVICE_MODE mode;
	public MyResponse(T t,Constants.SERVICE_MODE tMode)
	{
		responseFromServer = t;
		mode=tMode;
	}

}
