package com.example.moodapp.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class QuoteCollectionViewModel extends AndroidViewModel {
    private QuoteCollectionRepository qRepository;
    private LiveData<List<QuoteCollection>> allQuotes;
    public QuoteCollectionViewModel (Application application) {
        super(application);
        qRepository = new QuoteCollectionRepository(application);
        allQuotes = qRepository.getAllQuotes();
    }

    /** CompletableFuture **/
    // find by id
    public CompletableFuture<QuoteCollection> findByIDFuture(final int QuoteCollectionId){
        return qRepository.findByIDFuture(QuoteCollectionId);
    }

    /** FIND **/
    // get data
    public LiveData<List<QuoteCollection>> getAllQuotes() {
        return allQuotes;
    }

    /** CRUD **/
    // insert (add) data
    public void insertQuoteCollection(QuoteCollection quoteCollection) {
        qRepository.insertQuoteCollection(quoteCollection);
    }
    // delete whole data
    public void deleteAllQuoteCollection() {
        qRepository.deleteAllQuoteCollection();
    }
    // delete one data
    public void deleteQuoteCollection(QuoteCollection quoteCollection) {
        qRepository.deleteQuoteCollection(quoteCollection);
    }
    // update data
    public void updateMoodRecord(QuoteCollection quoteCollection) {
        qRepository.updateQuoteCollection(quoteCollection);
    }

}

