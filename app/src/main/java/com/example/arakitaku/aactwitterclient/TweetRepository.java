package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.arakitaku.aactwitterclient.livedatasample.ApiResponse;
import com.example.arakitaku.aactwitterclient.livedatasample.AppExecutors;
import com.example.arakitaku.aactwitterclient.livedatasample.NetworkBoundResource;
import com.example.arakitaku.aactwitterclient.livedatasample.Resource;

import java.util.List;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class TweetRepository {


    public LiveData<Resource<List<String>>> getTimeline() {
        return new NetworkBoundResource<List<String>, String>(new AppExecutors()) {

            @Override
            protected void saveCallResult(@NonNull String item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<String> data) {
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<String>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return null;
            }
        }.asLiveData();
    }

}
