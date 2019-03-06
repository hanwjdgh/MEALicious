package com.example.meal;

public class MenuItem {
    int name;
    int image;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public MenuItem(int name, int image) {
        this.name = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
