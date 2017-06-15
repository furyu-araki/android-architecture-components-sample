package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class MainViewModel extends ViewModel {

    public MainViewModel() {
    }

    public LiveData<String> getTimeline() {
        LiveData<String> liveData = new LiveData<String>() {
            @Override
            public void observe(LifecycleOwner owner, Observer<String> observer) {
                super.observe(owner, observer);
                observer.onChanged("hoge");
            }
        };
        return liveData;
    }

}
