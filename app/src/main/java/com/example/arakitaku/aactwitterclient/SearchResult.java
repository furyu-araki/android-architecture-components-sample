package com.example.arakitaku.aactwitterclient;

import java.util.ArrayList;
import java.util.List;

/**
 * 検索結果
 */
public class SearchResult {
    String query;
    List<Long> repositoryIds;

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

    public List<Long> getRepositoryIds() {
        return repositoryIds;
    }

    public void setRepositoryIds(List<Long> repositoryIds) {
        this.repositoryIds = repositoryIds;
    }
}
