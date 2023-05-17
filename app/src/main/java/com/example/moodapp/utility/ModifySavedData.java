package com.example.moodapp.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.moodapp.viewmodel.ViewModelController;

public class ModifySavedData {
    private static ModifySavedData instance;

    public static ModifySavedData getInstance() {
        if (instance == null) {
            instance = new ModifySavedData();
        }
        return instance;
    }
    private final static SharedPreferences sharedPreferences = ViewModelController.instance.getSharedPreferences("daName", Context.MODE_PRIVATE);



    //用户id
    public void setId(int id){
        sharedPreferences.edit().putInt("userId",id).apply();
    }

    public int getId(){
        return sharedPreferences.getInt("userId",1);
    }

}
