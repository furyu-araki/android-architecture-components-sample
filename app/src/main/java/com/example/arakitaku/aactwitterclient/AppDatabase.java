package com.example.arakitaku.aactwitterclient;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */
@Database(entities = {Repository.class, SearchResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepositoryDao repositoryDao();
}
