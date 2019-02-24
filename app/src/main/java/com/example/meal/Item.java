package com.example.meal;

public class Item {
    int image,image1;
    String title;

    public int getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }

    public Item(int image, int imag1, String title) {
        this.image = image;
        this.image1 = imag1;
        this.title = title;
    }
}
