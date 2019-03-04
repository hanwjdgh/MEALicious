package com.example.meal.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.meal.Fragment.AboutFragment;
import com.example.meal.Fragment.InformFragment;
import com.example.meal.Fragment.ReviewFragment;

public class DetailPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;

    public DetailPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new AboutFragment();
            case 1:
                return new InformFragment();
            case 2:
                return new ReviewFragment();
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
