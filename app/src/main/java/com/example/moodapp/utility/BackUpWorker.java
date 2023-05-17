package com.example.moodapp.utility;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.moodapp.database.MoodRecord;
import com.example.moodapp.database.MoodRecordRepository;
import com.example.moodapp.database.QuoteCollection;
import com.example.moodapp.database.QuoteCollectionRepository;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BackUpWorker extends Worker {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = mAuth.getCurrentUser();
    private String userId = currentUser.getUid();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String timestamp = dateFormat.format(new Date());

    public BackUpWorker(@NonNull Context context,
                        @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Log.i(TAG, "[" + timestamp + "] Back Up Worker started");
    }

    @NonNull
    @Override
    public Result doWork() {
        // get data from 2 database
        MoodRecordRepository mRepository = new MoodRecordRepository(
                (Application) getApplicationContext());
        QuoteCollectionRepository qRepository = new QuoteCollectionRepository((
                Application) getApplicationContext());
        List<MoodRecord> moodRecords = mRepository.getMoodRecords();
        Log.i(TAG, "[" + timestamp + "] fetch mood record database");
        List<QuoteCollection> quoteCollections = qRepository.getQuotes();
        Log.i(TAG, "[" + timestamp + "] fetch quote collection database");

        // check if database exists
        if(moodRecords.isEmpty() && quoteCollections.isEmpty()) {
            Log.d("work manager", "No database yet.");
            Log.i(TAG, "[" + timestamp + "] Result doWork failed");
            return Result.failure();
        }

        //back up start
        backUpToFirebase(moodRecords, quoteCollections);
        Log.d("work manager", "Back up successful.");
        Log.i(TAG, "[" + timestamp + "] called backUpToFirebase");
        return Result.success();
    }

    private void backUpToFirebase(List<MoodRecord> moodRecords,
                                  List<QuoteCollection> quoteCollections) {
        // loop each mood record
        for (MoodRecord moodRecord : moodRecords) {
            // get attributes from each instance
            String date = moodRecord.date;
            String mood = moodRecord.mood;
            String latitude = moodRecord.latitude;
            String longitude = moodRecord.longitude;
            String memo = moodRecord.memo;
            // write data
            ReadWriteFirebase.writeRecordsToFirebase(
                    userId, date, mood, latitude, longitude, memo);
        }

        Log.i(TAG, "[" + timestamp + "] called ReadWriteFirebase.writeRecordsToFirebase");
        // save quotation
            // write data
            ReadWriteFirebase.writeQuotesToFirebase(userId, quoteCollections);
            Log.i(TAG, "[" + timestamp + "] called ReadWriteFirebase.writeQuotesToFirebase");

    }

}



