package com.example.meal.Activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal.Adapter.ContentsPagerAdapter;
import com.example.meal.R;

public class ServiceActivity extends AppCompatActivity {
    private Context mContext;

    public static ViewPager mViewPager;
    public static  ContentsPagerAdapter mContentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        mContext = getApplicationContext();
        TabLayout mTaplayout = findViewById(R.id.layout_tab);

        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("서비스")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("오늘의 메뉴")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("주문하기")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("이벤트")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("뉴스")));

        mViewPager = findViewById(R.id.pager_content);
        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTaplayout.getTabCount());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mContentPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTaplayout));
        mTaplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0)
                    mContentPagerAdapter.notifyDataSetChanged();
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
        TextView txt_name = tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);
        txt_name.setTextSize(27);
        txt_name.setTextColor(Color.WHITE);
        return tabView;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.lag:
                return true;
            case R.id.eng:
                Toast.makeText(this, "영어", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.kor:
                Toast.makeText(this, "한국어", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cin:
                Toast.makeText(this, "중국어", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.jap:
                Toast.makeText(this, "일어", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.map:
                Dialog dialog = new Dialog(ServiceActivity.this);
                dialog.setContentView(R.layout.location_map);
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
