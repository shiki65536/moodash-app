package com.example.moodapp.database;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuoteCollection {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "quotation")
    @NonNull
    public  String quotation;

    public QuoteCollection
            (@NonNull String quotation) {
        this.quotation = quotation;
    }
}
