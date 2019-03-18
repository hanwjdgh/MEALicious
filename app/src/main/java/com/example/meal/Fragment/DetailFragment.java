package com.example.meal.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.meal.Activity.ServiceActivity;
import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Activity.WalletActivity;
import com.example.meal.Adapter.DetailPagerAdapter;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class DetailFragment extends Fragment {
    private Context mContext;
    TabLayout mTaplayout;
    ViewPager mViewpager;
    DetailPagerAdapter mDetailPagerAdapter;
    Button button;
    ImageView imageView, video, confirm,check_order_image, cancel;
    TextView check_order_name;
    int image[] = {R.drawable.meal_1, R.drawable.meal_2, R.drawable.meal_3};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Bitmap actionTitle = null;
        Bitmap imageBitmap = null;
        String name = "";
        int videoName = 0;

        int index = -1;
        if (bundle != null) {
            index = bundle.getInt("INDEX");
            actionTitle = bundle.getParcelable("ACTION");
            imageBitmap = bundle.getParcelable("IMAGE");
            name = bundle.getString("NAME");
            videoName = bundle.getInt("VIDEO");
        }

        final View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ((ImageView) view.findViewById(R.id.listImage)).setImageBitmap(imageBitmap);
        ((ImageView) view.findViewById(R.id.textView)).setImageBitmap(actionTitle);
        ((TextView) view.findViewById(R.id.menuname)).setText(name);

        mContext = getActivity().getApplicationContext();
        mTaplayout = view.findViewById(R.id.detailtab);
        mTaplayout.setSelectedTabIndicator(0);
        button = view.findViewById(R.id.obutton);
        video = view.findViewById(R.id.video);
        imageView = view.findViewById(R.id.select_message);

        if (TodayMenuActivity.mode == 1) {
            button.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServiceActivity.mViewPager.setCurrentItem(2);
                    getActivity().finish();
                }
            });
        } else if (TodayMenuActivity.mode == 3) {
            button.setVisibility(View.VISIBLE);
            final Bitmap finalImageBitmap = imageBitmap;
            final String finalName = name;
            final int finalIndex = index;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(getContext());
                    dialog.setContentView(R.layout.check_order);

                    confirm = dialog.findViewById(R.id.confirm);
                    cancel = dialog.findViewById(R.id.cancel);

                    check_order_name = dialog.findViewById(R.id.check_order_name);
                    check_order_name.setText(finalName);
                    check_order_image = dialog.findViewById(R.id.check_order_image);
                    check_order_image.setImageBitmap(finalImageBitmap);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 테두리 지움
                    dialog.show();

                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WalletActivity.cardsViews.get(0).setting();
                            WalletActivity.cardsViews.get(0).setString(finalName);
                            WalletActivity.cardsViews.get(0).setImage(image[finalIndex]);
                            getActivity().finish();
                            getActivity().overridePendingTransition(0, 0);
                        }
                    });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });

                }
            });
        }


        final int finalVideoName = videoName;
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.video_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 테두리 지움
                dialog.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.y = 35;
                params.width = 1500;

                dialog.getWindow().setAttributes(params);
                lp.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(lp);
                String uriPath = "android.resource://" + getContext().getPackageName() + "/" + finalVideoName;
                final VideoView videoview = (VideoView) dialog.findViewById(R.id.vv);
                ((Activity) getContext()).getWindow().setFormat(PixelFormat.TRANSLUCENT);
                videoview.setVideoURI(Uri.parse(uriPath));
                videoview.setZOrderOnTop(true);
                LinearLayout.LayoutParams pp = (LinearLayout.LayoutParams) videoview.getLayoutParams();

                pp.height = 900;
                videoview.setLayoutParams(pp);
                videoview.start();
            }
        });

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
