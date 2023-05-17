package com.example.moodapp.retrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public final class ImageUtils {
    private static final String baseUrl = "https://source.unsplash.com/800x0?";

    private OnImageListener onImageListener;


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
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(baseUrl+type)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                onImageListener.onFailure();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    InputStream inputStream = response.body() != null ? response.body().byteStream() : null;
                    //使用工厂把网络的输入流生产Bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    if (bitmap!= null){
                        //图片返回
                        onImageListener.onImage(bitmap);
                    }else {
                        onImageListener.onFailure();
                    }
                }catch (Exception e){
                    onImageListener.onFailure();
                }
            }
        });
    }

    public interface OnImageListener{
        void onImage(Bitmap bitmap);
        void onFailure();
    }

    public void setOnImageListener(OnImageListener onImageListener){
        this.onImageListener = onImageListener;
    }
}
