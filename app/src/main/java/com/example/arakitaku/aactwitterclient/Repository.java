package com.example.arakitaku.aactwitterclient;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */
@Entity
public class Repository {

    @PrimaryKey
    int id;

    String name;

    public Repository() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
