package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class MainViewModel extends ViewModel {

    public MainViewModel() {
    }

    public LiveData<List<String>> getTimeline() {
        LiveData<List<String>> liveData = new LiveData<List<String>>() {
            @Override
            public void observe(LifecycleOwner owner, Observer<List<String>> observer) {
                super.observe(owner, observer);
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 40; i++) {
                    list.add(i + " LiveData");
                }
                observer.onChanged(list);
            }
        };
        return liveData;
    }
}