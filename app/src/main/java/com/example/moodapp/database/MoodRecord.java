package com.example.moodapp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MoodRecord {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "date")
    @NonNull
    public String date;

    @ColumnInfo(name = "mood")
    @NonNull
    public String mood;

    @ColumnInfo(name = "latitude")
    @NonNull
    public String latitude;

    @ColumnInfo(name = "longitude")
    @NonNull
    public String longitude;

    @ColumnInfo(name = "memo")
    @NonNull
    public String memo;

    public MoodRecord
    (@NonNull String date, @NonNull String mood, @NonNull String latitude, @NonNull String longitude, String memo) {
        this.date = date;
        this.mood = mood;
        this.latitude = latitude;
        this.longitude = longitude;
        this.memo = memo;
    }

}
