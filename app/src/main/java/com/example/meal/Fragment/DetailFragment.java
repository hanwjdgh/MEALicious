package com.example.meal.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.meal.Activity.ServiceActivity;
import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Adapter.DetailPagerAdapter;
import com.example.meal.R;


public class DetailFragment extends Fragment {
    private Context mContext;
    TabLayout mTaplayout;
    ViewPager mViewpager;
    DetailPagerAdapter mDetailPagerAdapter;
    Button button;
    ImageView imageView, video, confirm,check_order_image, cancel;
    TextView check_order_name;
    int image[] = {R.drawable.meal_1, R.drawable.meal_2, R.drawable.meal_3};
    int point;

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
            point = bundle.getInt("TICKET");
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
                            ServiceActivity.cardsViews.get(point).setting();
                            ServiceActivity.cardsViews.get(point).setString(finalName);
                            ServiceActivity.cardsViews.get(point).setImage(image[finalIndex]);
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

        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("메뉴")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("정보")));
        mTaplayout.addTab(mTaplayout.newTab().setCustomView(createTabView("리뷰")));

        mViewpager = view.findViewById(R.id.detailpager);
        mDetailPagerAdapter = new DetailPagerAdapter(getActivity().getSupportFragmentManager(), mTaplayout.getTabCount(), index);
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setAdapter(mDetailPagerAdapter);

        TextView t = mTaplayout.getTabAt(0).getCustomView().findViewById(R.id.txt_name);
        t.setTextColor(Color.parseColor("#e9823e"));
        t.setTypeface(null, Typeface.BOLD);

        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTaplayout));
        mTaplayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());
                TextView t = mTaplayout.getTabAt(tab.getPosition()).getCustomView().findViewById(R.id.txt_name);
                t.setTextColor(Color.parseColor("#e9823e"));
                t.setTypeface(null, Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView t = mTaplayout.getTabAt(tab.getPosition()).getCustomView().findViewById(R.id.txt_name);
                t.setTextColor(Color.parseColor("#646464"));
                t.setTypeface(null, Typeface.NORMAL);

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
        txt_name.setTextSize(25);
        txt_name.setTextColor(Color.parseColor("#646464"));
        txt_name.setTypeface(null, Typeface.NORMAL);
        return tabView;
    }
}
