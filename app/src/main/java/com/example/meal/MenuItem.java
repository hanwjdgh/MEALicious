package com.example.meal;

public class MenuItem {
    int name;
    int image;
    String meal;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public MenuItem(int name, int image, String meal) {
        this.name = name;
        this.image = image;
        this.meal = meal;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

}
