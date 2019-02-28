package com.example.meal;

public class Item {
    int image;
    String title;

    public int getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }

    public Item(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
