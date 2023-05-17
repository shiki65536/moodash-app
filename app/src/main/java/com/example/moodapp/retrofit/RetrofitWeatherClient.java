package com.example.moodapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWeatherClient {
    private static Retrofit retrofit;

    public static RetrofitWeatherInterface getRetrofitInterface(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitWeatherInterface.class);
    }

}
