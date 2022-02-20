package com.sdomozhirov.loftmoneypro.cells;

public class Item {

    private String title;
    private int value;

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

