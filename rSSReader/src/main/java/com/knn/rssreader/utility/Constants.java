package com.knn.rssreader.utility;

public interface Constants
{

	int HTTP_CONNECT_TIMEOUT = 90000;
	int HTTP_READ_TIMEOUT = 90000;

	enum SERVICE_MODE
	{
		 NEWSLIST, CATEGORIES
	};

	interface IconnectApi
	{
		String REST_BASE_URL = "http://alpha.123boxoffice.com";

		String APP_NEWS_HEADLINES = "/headlines.json";

	}
	interface NetworkLoaderMessage {
        String LOADING_NEWS_MSG = "Loading news headlines..";
    }
	interface ToastMessage {
        String INVALID_EMAIL = "Please enter valid email";
        String FIELD_TYPE = "Please enter valid";
        String FIELD_BLANK = "Field cannot be left blank";
        String PASSWORD_LENGTH = "Please enter correct password";

        interface NetworkError{
            String RESPONSE_CODE_500="Unable to process request due to server down. Please try again later";
        }

    }
	
	interface ChangeFragments{
		int NEWS_LIST_FREAGMENT = 1;
		int NEWS_DETAIL_FREAGMENT = 2;
	}
}
