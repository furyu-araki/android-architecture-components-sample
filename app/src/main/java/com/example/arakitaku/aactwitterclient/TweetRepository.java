package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LiveData;
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

public class TweetRepository {


    public LiveData<Resource<SearchResult>> getTimeline() {
        return new NetworkBoundResource<SearchResult, SearchResult>(new AppExecutors()) {

            @Override
            protected void saveCallResult(@NonNull SearchResult item) {
                Log.d("saveCallResult", "saveCallResult");
            }

            @Override
            protected boolean shouldFetch(@Nullable SearchResult searchResult) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<SearchResult> loadFromDb() {
                LiveData<SearchResult> liveData = new LiveData<SearchResult>() {
                    @Override
                    protected void onActive() {
                        SearchResult searchResult = new SearchResult();
                        List<Repository> repositories = new ArrayList<>();
                        for (int i = 0; i < 40; i++) {
                            Repository repository = new Repository();
                            repository.setId(i);
                            repository.setName("レポジトリ：" + i);
                            repositories.add(repository);
                        }
                        searchResult.setRepositories(repositories);
                        setValue(searchResult);
                    }
                };
                return liveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<SearchResult>> createCall() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                        .build();
                GitHubService service = retrofit.create(GitHubService.class);
                return service.searchRepositories("Android");
            }

        }.asLiveData();
    }

}