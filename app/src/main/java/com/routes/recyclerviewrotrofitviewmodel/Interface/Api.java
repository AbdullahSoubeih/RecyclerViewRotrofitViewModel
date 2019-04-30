package com.routes.recyclerviewrotrofitviewmodel.Interface;



import com.routes.recyclerviewrotrofitviewmodel.Model.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getHeroes();
}
