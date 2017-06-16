package com.example.arakitaku.aactwitterclient;

import java.util.List;

/**
 * (c)FURYU CORP. 2017. All rights reserved.
 */
public class Repository {

    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public static class Item {
        long id;

        String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}