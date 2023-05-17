package com.example.moodapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuoteCollectionDAO {
    /** FIND **/
    //Find all: Live Data
    @Query("SELECT * FROM QuoteCollection")
    LiveData<List<QuoteCollection>> getAll();

    //Find all: List
    @Query("SELECT * FROM QuoteCollection")
    List<QuoteCollection> getAllCollections();

    //Find by id
    @Query("SELECT * FROM QuoteCollection WHERE uid =:id")
    QuoteCollection findByID(int id);

    /** CRUD **/
    @Insert
    void insertQuoteCollection(QuoteCollection quoteCollection);

    @Delete
    void deleteQuoteCollection(QuoteCollection quoteCollection);

    @Update
    void updateQuoteCollection(QuoteCollection quoteCollection);

    @Query("DELETE FROM quoteCollection")
    void deleteAll();
}

