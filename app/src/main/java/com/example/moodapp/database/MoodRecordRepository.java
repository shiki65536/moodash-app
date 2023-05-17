package com.example.moodapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class MoodRecordRepository {
    private MoodRecordDAO moodRecordDao;
    private LiveData<List<MoodRecord>> allMoodRecords;

    public MoodRecordRepository(Application application){
        MoodRecordDatabase db = MoodRecordDatabase.getInstance(application);
        moodRecordDao =db.moodRecordDao();
        allMoodRecords = moodRecordDao.getAll();
    }

    // Room executes this query on a separate thread
    public LiveData<List<MoodRecord>> getAllMoodRecords() {

        return allMoodRecords;
    }

    // Return normal List
    public List<MoodRecord> getMoodRecords() {
        return moodRecordDao.getAllRecords();
    }
    // Find by date
    public MoodRecord findMoodRecordByDate(String date) {
        return moodRecordDao.findByDate(date);
    }
    /** CRUD **/
    public void insertMoodRecord(final MoodRecord moodRecord){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            moodRecordDao.insertMoodRecord(moodRecord);
        } });
    }
    public void deleteAllMoodRecord(){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            moodRecordDao.deleteAll();
        } });
    }
    public void deleteMoodRecord(final MoodRecord moodRecord){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                moodRecordDao.deleteMoodRecord(moodRecord);
        } });
    }
    public void updateMoodRecord(final MoodRecord moodRecord){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            moodRecordDao.updateMoodRecord(moodRecord);
        } });
    }
    /** CompletableFuture **/
    //by ID
    public CompletableFuture<MoodRecord> findByIDFuture(final int moodRecordId) {
        return CompletableFuture.supplyAsync(new Supplier<MoodRecord>() {
            @Override
            public MoodRecord get() {
                return moodRecordDao.findByID(moodRecordId);
        }
        }, MoodRecordDatabase.databaseWriteExecutor);
    }

    //By date
    public CompletableFuture<MoodRecord> findByDateFuture(final String date) {
        return CompletableFuture.supplyAsync(new Supplier<MoodRecord>() {
            @Override
            public MoodRecord get() {

                return moodRecordDao.findByDate(date);
            }
        }, MoodRecordDatabase.databaseWriteExecutor);
    }


}
