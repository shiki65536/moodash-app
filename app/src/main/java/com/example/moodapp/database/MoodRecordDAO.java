package com.example.moodapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MoodRecordDAO {
    /** FIND **/
    //Find all: Live Data
    @Query("SELECT * FROM MoodRecord")
    LiveData <List<MoodRecord>> getAll();

    //Find all: List
    @Query("SELECT * FROM MoodRecord")
    List<MoodRecord> getAllRecords();

    //Find by uid
    @Query("SELECT * FROM MoodRecord WHERE uid =:id LIMIT 1")
//option:    SELECT * FROM customer WHERE uid = :customerId LIMIT 1"
    MoodRecord findByID(int id);

    //Find by mood
    @Query("SELECT * FROM MoodRecord WHERE mood LIKE:mood")
    List<MoodRecord> findByMood(String mood);

    //Find by date
    @Query("SELECT * FROM MoodRecord WHERE date =:date")
    MoodRecord findByDate(String date);


    /** CRUD **/
    @Insert
    void insertMoodRecord(MoodRecord moodRecord);

    @Delete
    void deleteMoodRecord(MoodRecord moodRecord);

    @Update
    void updateMoodRecord(MoodRecord moodRecord);

    @Query("DELETE FROM moodRecord")
    void deleteAll();
}



