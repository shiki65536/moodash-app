package com.example.moodapp.viewmodel;

import android.app.Application;

import com.example.moodapp.viewmodel.MoodRecordViewModel;
import com.example.moodapp.viewmodel.QuoteCollectionViewModel;

public class ViewModelController extends Application {
    public static Application instance;

    public static MoodRecordViewModel moodRecordViewModel;

    public static QuoteCollectionViewModel quoteCollectionViewModel;




    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        moodRecordViewModel = new MoodRecordViewModel(this);
        quoteCollectionViewModel = new QuoteCollectionViewModel(this);
    }
}
