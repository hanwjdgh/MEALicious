package com.example.meal.Activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.meal.Adapter.ContentsPagerAdapter;
import com.example.meal.R;

public class ServiceActivity extends AppCompatActivity {
    private Context mContext;

    public static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mContext = getApplicationContext();
        TabLayout mTaplayout = findViewById(R.id.layout_tab);

        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("서비스")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("주문하기")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("이벤트")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("뉴스")));

        mViewPager = findViewById(R.id.pager_content);
        ContentsPagerAdapter mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTaplayout.getTabCount());
        mViewPager.setAdapter(mContentPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTaplayout));
        mTaplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private View createTabView(String tabName) {

        View tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        return tabView;
    }

    @Override
    public void onBackPressed(){
        int curItem = mViewPager.getCurrentItem();
        if(curItem!=0)
            mViewPager.setCurrentItem(0,true);
        else
            super.onBackPressed();
    }
}
