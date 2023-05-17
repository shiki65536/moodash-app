package com.example.moodapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MoodRecord.class}, version = 2, exportSchema = false)
public abstract class MoodRecordDatabase extends RoomDatabase {
    public abstract MoodRecordDAO moodRecordDao();
    private static MoodRecordDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threads are not allowed to access data at the same time
    public static synchronized MoodRecordDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MoodRecordDatabase.class, "MoodRecordDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE; }
}
