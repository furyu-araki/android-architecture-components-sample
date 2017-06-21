package com.example.arakitaku.aactwitterclient;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

/**
 * 検索結果
 */
@Entity
@TypeConverters(IntegerListConverter.class)
public class SearchResult {

    @PrimaryKey
    String query;

    List<Integer> repositoryIds;

    public SearchResult() {
    }

    public SearchResult(String query, List<Repository> repositories) {
        this.query = query;
        this.repositoryIds = new ArrayList<>();
        for (Repository repository : repositories) {
            repositoryIds.add(repository.getId());
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getRepositoryIds() {
        return repositoryIds;
    }

    public void setRepositoryIds(List<Integer> repositoryIds) {
        this.repositoryIds = repositoryIds;
    }
}
