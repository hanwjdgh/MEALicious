package com.example.meal.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.meal.Fragment.AboutFragment;
import com.example.meal.Fragment.InformFragment;
import com.example.meal.Fragment.ReviewFragment;

public class DetailPagerAdapter extends FragmentStatePagerAdapter {
    private int mPageCount;
    int index;

    public DetailPagerAdapter(FragmentManager fm, int pageCount, int point) {
        super(fm);
        this.mPageCount = pageCount;
        this.index = point;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                AboutFragment aboutFragment = new AboutFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("INDEX",index);
                aboutFragment.setArguments(bundle);
                return aboutFragment;
            case 1:
                InformFragment informFragment = new InformFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("INDEX",index);
                informFragment.setArguments(bundle1);
                return informFragment;
            case 2:
                ReviewFragment reviewFragment = new ReviewFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("INDEX",index);
                reviewFragment.setArguments(bundle2);
                return reviewFragment;
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
