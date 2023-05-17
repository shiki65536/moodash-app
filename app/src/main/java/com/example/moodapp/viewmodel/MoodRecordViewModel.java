package com.example.moodapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moodapp.database.MoodRecord;
import com.example.moodapp.database.MoodRecordRepository;

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
    public CompletableFuture<MoodRecord> findByIDFuture(final int MoodRecordId){
        return mRepository.findByIDFuture(MoodRecordId);
    }
    public LiveData<List<MoodRecord>> getAllMoodRecords() {
        return allMoodRecords;
    }
    public void insertMoodRecord(MoodRecord moodRecord) {
        mRepository.insertMoodRecord(moodRecord);
    }
    public void deleteAllMoodRecord() {
        mRepository.deleteAllMoodRecord();
    }
    public void updateMoodRecord(MoodRecord moodRecord) {
        mRepository.updateMoodRecord(moodRecord);
    }


}
