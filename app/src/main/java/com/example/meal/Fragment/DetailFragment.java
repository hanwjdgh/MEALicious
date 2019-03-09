package com.example.meal.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meal.Activity.MenuDetailActivity;
import com.example.meal.Activity.ServiceActivity;
import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Adapter.DetailPagerAdapter;
import com.example.meal.R;

public class DetailFragment extends Fragment {
    public ViewPager mViewPager;
    private Context mContext;
    TabLayout mTaplayout;
    ViewPager mViewpager;
    DetailPagerAdapter mDetailPagerAdapter;
    Button button;
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Bitmap actionTitle = null;
        Bitmap imageBitmap = null;

        if (bundle != null) {
            actionTitle = bundle.getParcelable("ACTION");
            imageBitmap = bundle.getParcelable("IMAGE");
        }

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ((ImageView) view.findViewById(R.id.listImage)).setImageBitmap(imageBitmap);
        ((ImageView) view.findViewById(R.id.textView)).setImageBitmap(actionTitle);
        mContext = getActivity().getApplicationContext();
        mTaplayout = view.findViewById(R.id.detailtab);
        button = view.findViewById(R.id.obutton);
        imageView = view.findViewById(R.id.select_message);

        if(TodayMenuActivity.mode == 1) {
            button.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServiceActivity.mViewPager.setCurrentItem(2);
                    getActivity().finish();
                }
            });
        }
        else if(TodayMenuActivity.mode==3) {
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("주문 하실??");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.setNegativeButton("아니오",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.show();
                }
            });
        }
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
