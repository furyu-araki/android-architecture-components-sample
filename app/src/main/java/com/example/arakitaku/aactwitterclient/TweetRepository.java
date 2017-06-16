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


    public LiveData<Resource<Repository>> getTimeline() {
        return new NetworkBoundResource<Repository, Repository>(new AppExecutors()) {

            @Override
            protected void saveCallResult(@NonNull Repository item) {
                Log.d("saveCallResult", "saveCallResult");
            }

            @Override
            protected boolean shouldFetch(@Nullable Repository repository) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Repository> loadFromDb() {
                LiveData<Repository> liveData = new LiveData<Repository>() {
                    @Override
                    protected void onActive() {
                        Repository repository = new Repository();
                        List<Repository.Item> items = new ArrayList<>();
                        for (int i = 0; i < 40; i++) {
                            Repository.Item item = new Repository.Item();
                            item.setId(i);
                            item.setName("レポジトリ：" + i);
                            items.add(item);
                        }
                        repository.setItems(items);
                        setValue(repository);
                    }
                };
                return liveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Repository>> createCall() {
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