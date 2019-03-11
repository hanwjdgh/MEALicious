package com.example.meal;

public class Item {
    int image;
    String title, air, start, finish;
    String[] inform;

    public int getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAir(){return this.air;}
    public String[] getInform(){return this.inform;}
    public String getStart(){return this.start;}
    public String getFinish(){return this.finish;}


    public Item(int image, String title, String air, String[] str, String st, String fi) {
        this.image = image;
        this.title = title;
        this.air = air;
        this.inform = str;
        this.start = st;
        this.finish = fi;
    }
}
