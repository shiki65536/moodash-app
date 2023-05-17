package com.example.moodapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moodapp.retrofit.ImageResponse;
import com.example.moodapp.retrofit.QuoteBean;
import com.example.moodapp.retrofit.QuoteNImageRetrofitClient;
import com.example.moodapp.retrofit.RetrofitImageClient;
import com.example.moodapp.retrofit.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteNImageViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    private final static String baseQuoteUrl = "https://api.api-ninjas.com/v1/";

    private final static String API_QuoteKey = "TZjhqQoEAjhF7/clVDxHNQ==Rg9iykC5Sa9Gcus9";

    private final static String baseImageUrl = "https://api.unsplash.com/";
    private final static String API_ImageKey = "q6N59FJWx2Dfr6VWYQv_StqmLzWVi1-_HDv2FztXHy8";

    public MutableLiveData<WeatherResponse> weatherResponse = new MutableLiveData<>();//livedata天气类

    public MutableLiveData<List<QuoteBean>> quoteResponse = new MutableLiveData<>();//鸡汤天气类

    public MutableLiveData<ImageResponse> imageResponse = new MutableLiveData<>();
    public QuoteNImageViewModel(){
        mText = new MutableLiveData<String>();
    }
    public void setMessage(String message) { mText.setValue(message);
    }
    public LiveData<String> getText() {
        return mText;
    }


    public void getQuote(String mood){
        String type = "";
        switch (mood){
            case "Happy":
                type = "happiness";
                break;
            case "Excited":
                type = "amazing";
                break;
            case "Relax":
                type = "freedom";
                break;
            case "Celebrate":
                type = "friendship";
                break;
            case "Sad":
                type = "faith";
                break;
            case "Angry":
                type = "anger";
                break;
            case "Bored":
                type = "inspirational";
                break;
            case "Hurt":
                type = "hope";
                break;
        }
        QuoteNImageRetrofitClient.getRetrofitClient(baseQuoteUrl).fetchQuote(API_QuoteKey,type).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    //获取成功，利用gson转成数据类，然后显示在UI上面
                    try {
                        Gson gson = new Gson();//Gson工具，将json转化为实体类，也就是entity
                        List<QuoteBean> quoteBeanList;//因为返回数据是鸡汤链表，所以这里也是链表的形式
                        if (response.body() != null){
                            quoteBeanList = gson.fromJson(response.body().string(), new TypeToken<List<QuoteBean>>() {}.getType());//转化实体类
                            quoteResponse.postValue(quoteBeanList);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void getImage(String mood){
        String type = "";
        switch (mood) {
            case "Happy":
                type = "natural view";
                break;
            case "Excited":
                type = "waterfalls wallpapers";
                break;
            case "Relax":
                type = "countryside wallpapers";
                break;
            case "Celebrate":
                type = "Hd sky wallpapers";
                break;
            case "Sad":
                type = "lake wallpapers";
                break;
            case "Angry":
                type = "sea wave wallpapers";
                break;
            case "Bored":
                type = "travel";
                break;
            case "Hurt":
                type = "forest  wallpapers";
                break;
        }

        RetrofitImageClient.getRetrofitClient(baseImageUrl).getImage(API_ImageKey,1,type).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful()){
                    imageResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }

}