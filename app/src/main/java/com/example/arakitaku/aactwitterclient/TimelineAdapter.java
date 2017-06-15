package com.example.arakitaku.aactwitterclient;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.TweetViewHolder> {

    List<String> tweetList;

    public TimelineAdapter(List<String> tweetList) {
        this.tweetList = tweetList;
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup root, int viewType) {
        return TweetViewHolder.create(root);
    }

    @Override
    public void onBindViewHolder(TweetViewHolder tweetViewHolder, int position) {
        tweetViewHolder.bodyTextView.setText(tweetList.get(position));
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    static class TweetViewHolder extends RecyclerView.ViewHolder {

        TextView bodyTextView;

        public TweetViewHolder(View itemView) {
            super(itemView);
            bodyTextView = (TextView) itemView.findViewById(R.id.cell_tweet_textView_body);
        }

        static TweetViewHolder create(ViewGroup root) {
            View view = LayoutInflater.from(root.getContext()).inflate(R.layout.cell_tweet, root, false);
            return new TweetViewHolder(view);
        }

    }
}