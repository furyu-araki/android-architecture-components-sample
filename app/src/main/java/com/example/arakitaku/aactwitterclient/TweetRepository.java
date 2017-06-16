package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.arakitaku.aactwitterclient.livedatasample.ApiResponse;
import com.example.arakitaku.aactwitterclient.livedatasample.AppExecutors;
import com.example.arakitaku.aactwitterclient.livedatasample.NetworkBoundResource;
import com.example.arakitaku.aactwitterclient.livedatasample.Resource;

import java.util.ArrayList;
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
                LiveData<List<String>> liveData = new LiveData<List<String>>() {
                    @Override
                    protected void onActive() {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 40; i++) {
                            list.add(i + " LiveData");
                        }
                        setValue(list);
                    }
                };
                return liveData;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<String>> createCall() {
                return null;
            }
        }.asLiveData();
    }

}
