package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */
@Dao
public interface RepositoryDao {

    @Query("SELECT * FROM Repository WHERE id in (:repositoryIds)")
    LiveData<List<Repository>> getByIds(List<Integer> repositoryIds);

    @Query("SELECT * FROM SearchResult WHERE query = :query")
    LiveData<SearchResult> search(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Repository> repositories);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SearchResult searchResult);
}