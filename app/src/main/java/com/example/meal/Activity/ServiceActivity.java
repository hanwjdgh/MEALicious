package com.example.meal.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.meal.Adapter.ContentsPagerAdapter;
import com.example.meal.R;

public class ServiceActivity extends AppCompatActivity {
    public static String menu_title[]={"한국어","English","汉语","日本語"};
    private Context mContext;
    public static int check_lan = 0;
    public static ViewPager mViewPager;
    public static  ContentsPagerAdapter mContentPagerAdapter;
    TabLayout mTaplayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        mContext = getApplicationContext();
        mTaplayout = findViewById(R.id.layout_tab);

        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("서비스")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("오늘의 메뉴")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("주문하기")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("소식")));


        mViewPager = findViewById(R.id.pager_content);
        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTaplayout.getTabCount());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mContentPagerAdapter);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTaplayout));
        mViewPager.setCurrentItem(SelectActivity.s_num);
        TextView t = mTaplayout.getTabAt(SelectActivity.s_num).getCustomView().findViewById(R.id.txt_name);
        t.setTypeface(null, Typeface.BOLD);
        t.setTextColor(Color.parseColor("#646464"));
        mTaplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                View v = tab.getCustomView();
                TextView t = v.findViewById(R.id.txt_name);
                t.setTypeface(null, Typeface.BOLD);
                t.setTextColor(Color.parseColor("#646464"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View v = tab.getCustomView();
                TextView t = v.findViewById(R.id.txt_name);
                t.setTextSize(27);
                t.setTextColor(Color.parseColor("#45FCFBFB"));
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

        return tabView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        menu.getItem(0).getSubMenu().getItem(check_lan).setChecked(true);
        menu.getItem(0).setTitle("language");
        for (int i = 0; i < 4; i++)
            menu.getItem(0).getSubMenu().getItem(i).setTitle(menu_title[i]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                ;
                startActivity(homeIntent);
                return true;
            case R.id.lag:
                return true;
            case R.id.kor:
                check_lan = 0;
                invalidateOptionsMenu();
                return true;
            case R.id.eng:
                check_lan = 1;
                invalidateOptionsMenu();
                return true;
            case R.id.cin:
                check_lan = 2;
                invalidateOptionsMenu();
                return true;
            case R.id.jap:
                check_lan = 3;
                invalidateOptionsMenu();
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
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
