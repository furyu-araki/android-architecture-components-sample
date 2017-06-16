package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.arakitaku.aactwitterclient.livedatasample.Resource;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class MainViewModel extends ViewModel {

    public MainViewModel() {
    }

    public LiveData<Resource<Repository>> getTimeline() {
        TweetRepository tweetRepository = new TweetRepository();
        return tweetRepository.getTimeline();
    }
}