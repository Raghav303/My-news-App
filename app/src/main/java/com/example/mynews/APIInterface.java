package com.example.mynews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey,
            @Query("category") String category,
            @Query("sortBy") String orderBy
            );
    @GET("everything")
    Call<Headlines> getEverything(
            @Query("q") String query,
            @Query("apiKey") String apiKey
    );
}
