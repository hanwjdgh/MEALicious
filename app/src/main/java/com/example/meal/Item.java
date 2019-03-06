package com.example.meal;

public class Item {
    int image;
    String title, air;

    public int getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAir(){return this.air;}

    public Item(int image, String title, String air) {
        this.image = image;
        this.title = title;
        this.air = air;
    }
}
