package com.example.moodapp.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("quotes/")
    Call<ResponseBody> fetchQuote(@Header("X-Api-Key") String key, @Query("category") String category);

    @GET("search/photos/")
    Call<ImageResponse> getImage(
            @Query("client_id") String key,
            @Query("per_page") int page,
            @Query("query")String query
    );
}
