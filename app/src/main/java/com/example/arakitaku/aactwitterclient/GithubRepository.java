package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.arakitaku.aactwitterclient.livedatasample.ApiResponse;
import com.example.arakitaku.aactwitterclient.livedatasample.AppExecutors;
import com.example.arakitaku.aactwitterclient.livedatasample.LiveDataCallAdapterFactory;
import com.example.arakitaku.aactwitterclient.livedatasample.NetworkBoundResource;
import com.example.arakitaku.aactwitterclient.livedatasample.Resource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class GithubRepository {

    RepositoryDao repositoryDao;

    public GithubRepository(Context context) {
        MainApplication application = (MainApplication) context.getApplicationContext();
        AppDatabase database = application.getDatabase();
        repositoryDao = database.repositoryDao();
    }

    public LiveData<Resource<List<Repository>>> getTimeline(String query) {
        return new NetworkBoundResource<List<Repository>, SearchResultDto>(new AppExecutors()) {

            @Override
            protected void saveCallResult(@NonNull SearchResultDto searchResultDto) {
                repositoryDao.insert(searchResultDto.getRepositories());
                repositoryDao.insert(new SearchResult(query, searchResultDto.getRepositories()));
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Repository> searchResult) {
                return searchResult == null;
            }

            @NonNull
            @Override
            protected LiveData<List<Repository>> loadFromDb() {
                // クエリからSearchResultを取得
                repositoryDao.search(query);

                return Transformations.switchMap(repositoryDao.search(query), searchResult -> {
                    if (searchResult == null) {
                        // Resultがなければ、空のLiveDataを流す → APIが呼ばれる
                        return new AbsentLiveData();
                    } else {
                        // Resultがあれば、そのIDリストからRepositoryのリストをDBから取得して流す → キャッシュが利用される
                        return repositoryDao.getByIds(searchResult.getRepositoryIds());
                    }
                });
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SearchResultDto>> createCall() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                        .build();
                GitHubService service = retrofit.create(GitHubService.class);
                return service.searchRepositories(query);
            }

        }.asLiveData();
    }

}