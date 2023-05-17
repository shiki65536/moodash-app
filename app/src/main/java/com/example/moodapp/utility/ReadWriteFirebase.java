package com.example.moodapp.utility;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.moodapp.database.MoodRecord;
import com.example.moodapp.database.QuoteCollection;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ReadWriteFirebase {

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference dbRef = database.getReference();


    // write mood record to firebase
    public static void writeRecordsToFirebase(String userId, String date, String mood,
                                              String latitude, String longitude,
                                              String memo) {
    MoodRecord moodRecord = new MoodRecord(date, mood, latitude, longitude, memo);
        dbRef.child("user").child(userId).child(date).setValue(moodRecord);

    }

    // write quotation to firebase
    public static void writeQuotesToFirebase(String userId, List<QuoteCollection> quote) {

        dbRef.child("user").child(userId).child("collection")
                .setValue(quote);
}
}
