package com.knn.rssreader.network;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

import com.knn.rssreader.adapter.NewsHeadlinesAdapter;
import com.knn.rssreader.utility.Constants;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by root on 9/1/15.
 */
public class RestApiConfiguration {

    private static RestApiConfiguration restApiConfiguration;
    private static RestService restService;


    public static RestApiConfiguration getInstance() {
        if (restApiConfiguration == null) {
            restApiConfiguration = new RestApiConfiguration();
        }
        return restApiConfiguration;
    }

    private RestApiConfiguration() {
        RestAdapter restAdapter = buildRestAdapter();
        restService = restAdapter.create(RestService.class);

    }


    private RestAdapter buildRestAdapter() {
        return new RestAdapter.Builder().setEndpoint(Constants.IconnectApi.REST_BASE_URL).setClient(getHttpClient()).setLogLevel(RestAdapter.LogLevel.FULL).build();
        //setConverter(new JacksonConverter(new ObjectMapper()))
    }

    /**
     * Custom Http Client to define connection timeouts.
     */
    private Client getHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();

        httpClient.setConnectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.setReadTimeout(Constants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        return new OkClient(httpClient);
    }

    public void getNewsHeadlines( Callback<NewsHeadlinesAdapter> callback) {
        restService.getNewsHeadlines( callback);
    }
//
//    public void appAuthenticate(String storeID, String pin, Callback<Response> callback) {
//        restService.appAuthenticate(storeID, pin, callback);
//    }
//
//
//    public void getDepartments(String storeId, String userId, Callback<Department> departmentCallback) {
//        restService.getDepartments(storeId, userId, departmentCallback);
//    }
//
//    public void getCategories(String storeId, String userId, String departmentId, Callback<Categories> callback) {
//        restService.getCategoriesByDepartment(storeId, userId, departmentId, callback);
//    }





}
