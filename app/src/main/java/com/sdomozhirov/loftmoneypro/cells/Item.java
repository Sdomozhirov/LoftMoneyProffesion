package com.sdomozhirov.loftmoneypro.cells;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("name") private String title;
    @SerializedName("price") private int value;

    public Item(final String title, final int value) {
        this.title = title;
        this.value = value;}

        public String getTitle () {
            return title;
        }

        public int getValue () {
            return value;
        }

    }

