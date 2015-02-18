package com.knn.rssreader.network;

import retrofit.Callback;
import retrofit.http.GET;

import com.knn.rssreader.adapter.NewsHeadlinesAdapter;
import com.knn.rssreader.utility.Constants;

public interface RestService {

//    @FormUrlEncoded
//    @POST(Constants.IconnectApi.APP_LOGIN)
//    void storeLogin(Callback<String> callback);
    
    @GET(Constants.IconnectApi.APP_NEWS_HEADLINES)
	void getNewsHeadlines(Callback<NewsHeadlinesAdapter> callback);

//    @Headers({
//            "SOAPAction : http://www.webserviceX.NET/GetWeather",
//            "Host: www.webserviceX.NET"
//    })
//    @POST("/globalweather.asmx")
//    void getWeather(@Body RequestEnvelope requestEnvelope, Callback<MyTestResponse> callback);
//
//
//    @POST("/MerchantWebServices-v1?wsdl")
//    void creditCardAuth(@Body CreditTransactionEnvelope creditTransactionEnvelope, Callback<CreditCardAuthResponse> callback);
//
//
//    @POST("/MerchantWebServices-v1?wsdl")
//    @Headers({
//            "Content-Type : text/xml"
//    })
//    void creditCardAuth(@Body TypedByteArray creditTransactionEnvelope, Callback<CreditCardAuthResponse> callback);

}
