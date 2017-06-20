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

public class GithubRepository {


    public LiveData<Resource<List<Repository>>> getTimeline() {
        return new NetworkBoundResource<List<Repository>, SearchResultDto>(new AppExecutors()) {

            @Override
            protected void saveCallResult(@NonNull SearchResultDto item) {
                Log.d("saveCallResult", "saveCallResult");
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Repository> searchResult) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Repository>> loadFromDb() {
                LiveData<List<Repository>> liveData = new LiveData<List<Repository>>() {
                    @Override
                    protected void onActive() {
                        List<Repository> repositories = new ArrayList<>();
                        for (int i = 0; i < 40; i++) {
                            Repository repository = new Repository();
                            repository.setId(i);
                            repository.setName("レポジトリ：" + i);
                            repositories.add(repository);
                        }
                        setValue(repositories);
                    }
                };
                return liveData;
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
                return service.searchRepositories("Android");
            }

        }.asLiveData();
    }

}