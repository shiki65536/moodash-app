package com.example.moodapp.retrofit;

import retrofit2.Retrofit;

public class QuoteNImageRetrofitClient {
    private static Retrofit retrofit;



    public static RetrofitInterface getRetrofitClient(String baseUrl){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(RetrofitInterface.class);
    }

}
