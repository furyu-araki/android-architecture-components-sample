/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */
package com.example.arakitaku.aactwitterclient;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterConfig config = new TwitterConfig.Builder(this)
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_CONSUMER_KEY, BuildConfig.TWITTER_CONSUMER_SECRET))
                .debug(BuildConfig.DEBUG)
                .build();
        Twitter.initialize(config);
    }
}