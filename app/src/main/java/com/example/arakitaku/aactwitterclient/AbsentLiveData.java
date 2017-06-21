package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LiveData;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class AbsentLiveData extends LiveData {

    public AbsentLiveData() {
        postValue(null);
    }

}
