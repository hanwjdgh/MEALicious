package com.example.meal.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.meal.Fragment.NewsFragment;
import com.example.meal.Fragment.MenuFragment;
import com.example.meal.Fragment.OrderFragment;
import com.example.meal.Fragment.ServiceFragment;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ServiceFragment serviceFragment = new ServiceFragment();
                return serviceFragment;
            case 1:
                MenuFragment menuFragment = new MenuFragment();
                return menuFragment;
            case 2:
                OrderFragment orderFragment = new OrderFragment();
                return orderFragment;
            case 3:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mPageCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}

