package com.example.moodapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {QuoteCollection.class}, version = 1, exportSchema = false)
public abstract class QuoteCollectionDatabase extends RoomDatabase {
    public abstract QuoteCollectionDAO quoteCollectionDao();
    private static QuoteCollectionDatabase INSTANCE;
    //we create an ExecutorService with a fixed thread pool so we can later run database operations asynchronously on a background thread.
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    //A synchronized method in a multi threaded environment means that two threads are not allowed to access data at the same time
    public static synchronized QuoteCollectionDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QuoteCollectionDatabase.class, "QuoteCollectionDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE; }
}

