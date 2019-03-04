package com.example.meal.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meal.Adapter.DetailPagerAdapter;
import com.example.meal.R;

public class DetailFragment extends Fragment {
    public ViewPager mViewPager;
    private Context mContext;
    TabLayout mTaplayout;
    ViewPager mViewpager;
    DetailPagerAdapter mDetailPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String actionTitle = "DUMMY ACTION";
        Bitmap imageBitmap = null;

        if (bundle != null) {
            actionTitle = bundle.getString("ACTION");
            imageBitmap = bundle.getParcelable("IMAGE");
        }

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ((ImageView) view.findViewById(R.id.listImage)).setImageBitmap(imageBitmap);
        ((TextView) view.findViewById(R.id.textView)).setText(actionTitle);
        mContext = getActivity().getApplicationContext();
        mTaplayout = view.findViewById(R.id.detailtab);
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("About")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("Inform")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("Review")));

        mViewpager = view.findViewById(R.id.detailpager);
        mDetailPagerAdapter = new DetailPagerAdapter(getActivity().getSupportFragmentManager(), mTaplayout.getTabCount());
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setAdapter(mDetailPagerAdapter);

        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTaplayout));
        mTaplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView txt_name = tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        txt_name.setTextSize(27);
        txt_name.setTextColor(Color.BLACK);
        return tabView;
    }
}
