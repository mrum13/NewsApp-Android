package com.inreadyworkgroup.newsapp.Rest;

import com.inreadyworkgroup.newsapp.Model.GetNews;
import com.inreadyworkgroup.newsapp.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<GetNews> getNews(@Query("country")String country, @Query("apiKey")String api);
}

