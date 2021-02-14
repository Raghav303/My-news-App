package com.example.mynews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String baseUrl ="https://newsapi.org/v2/";
    private Retrofit  retrofit;
    private static APIClient apiClient;

    private APIClient()
    {
        retrofit = new Retrofit.Builder().
                   baseUrl(baseUrl).
                   addConverterFactory(GsonConverterFactory.create()).
                   build();
    }
    public static synchronized APIClient getInstance()
    {
        if(apiClient==null) {
            return new APIClient();
        }

        return apiClient;
    }
    public APIInterface getApi(){
        return retrofit.create(APIInterface.class);
    }

}
