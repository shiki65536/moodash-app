package com.example.moodapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moodapp.retrofit.RetrofitWeatherClient;
import com.example.moodapp.retrofit.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private static final String APY_KEY = "48f142c16b609d78bdb45cc7d9514a94";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    public MutableLiveData<WeatherResponse> weatherResponse = new MutableLiveData<>();//livedata天气类
    public WeatherViewModel(){
        mText = new MutableLiveData<String>();
    }
    public void setMessage(String message) { mText.setValue(message);
    }
    public LiveData<String> getText() {
        return mText;
    }

    public void getWeather(double lat,double lon){
        RetrofitWeatherClient.getRetrofitInterface(BASE_URL).getWeather(lat,lon,APY_KEY).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()){
                    weatherResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }
}