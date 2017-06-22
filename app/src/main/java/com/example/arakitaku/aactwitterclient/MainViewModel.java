package com.example.arakitaku.aactwitterclient;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.arakitaku.aactwitterclient.livedatasample.Resource;

import java.util.List;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class MainViewModel extends AndroidViewModel {

    private GithubRepository githubRepository;

    public MainViewModel(Application application) {
        super(application);
        githubRepository = new GithubRepository(application);
    }

    public LiveData<Resource<List<Repository>>> getTimeline() {
        return githubRepository.getTimeline("View");
    }

}