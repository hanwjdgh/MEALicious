package com.example.meal;

import android.os.Parcel;
import android.os.Parcelable;

public class WalletItem implements Parcelable {
    private String text;
    private String color;

    public WalletItem(String text, String color) {
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.color);
    }

    private WalletItem(Parcel in) {
        this.text = in.readString();
        this.color = in.readString();
    }

    public static final Parcelable.Creator<WalletItem> CREATOR = new Parcelable.Creator<WalletItem>() {
        @Override
        public WalletItem createFromParcel(Parcel source) {
            return new WalletItem(source);
        }

        @Override
        public WalletItem[] newArray(int size) {
            return new WalletItem[size];
        }
    };
}