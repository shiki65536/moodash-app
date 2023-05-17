package com.example.moodapp.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MoodRecordViewModel extends AndroidViewModel {
    private MoodRecordRepository mRepository;
    private LiveData<List<MoodRecord>> allMoodRecords;
    public MoodRecordViewModel (Application application) {
        super(application);
        mRepository = new MoodRecordRepository(application);
        allMoodRecords = mRepository.getAllMoodRecords();
    }

    /** CompletableFuture **/
    // find by id: Live Data
    public CompletableFuture<MoodRecord> findByIDFuture(final int MoodRecordId){
        return mRepository.findByIDFuture(MoodRecordId);
    }
    // find by date: Live Data
    public CompletableFuture<MoodRecord> findByDateFuture(final String date){
        return mRepository.findByDateFuture(date);
    }

    /** FIND **/
    // get data
    public LiveData<List<MoodRecord>> getAllMoodRecords() {
        return allMoodRecords;
    }

    // find date
    public MoodRecord findMoodRecordByDate(String date){
        return mRepository.findMoodRecordByDate(date);
    }

    /** CRUD **/
    // insert (add) data
    public void insertMoodRecord(MoodRecord moodRecord) {
        mRepository.insertMoodRecord(moodRecord);
    }
    // delete whole data
    public void deleteAllMoodRecord() {
        mRepository.deleteAllMoodRecord();
    }
    // delete one daa
    public void deleteMoodRecord(MoodRecord moodRecord) {
        mRepository.deleteMoodRecord(moodRecord);
    }
    // update data
    public void updateMoodRecord(MoodRecord moodRecord) {
        mRepository.updateMoodRecord(moodRecord);
    }

}
