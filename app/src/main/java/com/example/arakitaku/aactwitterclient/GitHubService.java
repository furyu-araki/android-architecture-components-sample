package com.example.arakitaku.aactwitterclient;

import android.arch.lifecycle.LiveData;

import com.example.arakitaku.aactwitterclient.livedatasample.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */
public interface GitHubService {

    @GET("search/repositories")
    LiveData<ApiResponse<SearchResultDto>> searchRepositories(@Query("q") String query);

}