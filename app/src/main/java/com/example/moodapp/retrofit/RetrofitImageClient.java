package com.example.moodapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImageClient {

    private static Retrofit retrofit;

    public static RetrofitInterface getRetrofitClient(String baseImageUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseImageUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitInterface.class);
    }
}
