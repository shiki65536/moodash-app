package com.example.moodapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class QuoteCollectionRepository {
    private QuoteCollectionDAO quoteCollectionDao;
    private LiveData<List<QuoteCollection>> allQuotes;

    public QuoteCollectionRepository(Application application){
        QuoteCollectionDatabase db = QuoteCollectionDatabase.getInstance(application);
        quoteCollectionDao =db.quoteCollectionDao();
        allQuotes = quoteCollectionDao.getAll();
    }

    // Room executes this query on a separate thread
    public LiveData<List<QuoteCollection>> getAllQuotes() {
        return allQuotes;
    }
    // Return normal List
    public List<QuoteCollection> getQuotes() {
        return quoteCollectionDao.getAllCollections();
    }
    /** CRUD **/
    public void insertQuoteCollection(final QuoteCollection quoteCollection){
        QuoteCollectionDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quoteCollectionDao.insertQuoteCollection(quoteCollection);
            } });
    }
    public void deleteAllQuoteCollection(){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quoteCollectionDao.deleteAll();
            } });
    }
    public void deleteQuoteCollection(final QuoteCollection quoteCollection){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {

                quoteCollectionDao.deleteQuoteCollection(quoteCollection);
            } });
    }
    public void updateQuoteCollection(final QuoteCollection quoteCollection){
        MoodRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                quoteCollectionDao.updateQuoteCollection(quoteCollection);
            } });
    }

    /** CRUD **/
    public CompletableFuture<QuoteCollection> findByIDFuture(final int quoteCollectionId) {
        return CompletableFuture.supplyAsync(new Supplier<QuoteCollection>() {
            @Override
            public QuoteCollection get() {

                return quoteCollectionDao.findByID(quoteCollectionId);
            }
        }, MoodRecordDatabase.databaseWriteExecutor);
    }

}
