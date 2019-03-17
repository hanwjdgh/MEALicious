package com.example.meal.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.meal.Activity.MainActivity;
import com.example.meal.Activity.ServiceActivity;
import com.example.meal.R;

public class ServiceFragment extends Fragment {
    private int animationCounter = 1;
    private Handler imageSwitcherHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        final ImageSwitcher imageSwitcher = view.findViewById(R.id.service);
        ImageView button = view.findViewById(R.id.next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.mViewPager.setCurrentItem(2);
            }
        });

        Animation in  = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

        imageSwitcherHandler = new Handler(Looper.getMainLooper());
        imageSwitcherHandler.post(new Runnable() {
            @Override
            public void run() {
                switch (animationCounter++) {
                    case 1:
                        imageSwitcher.setImageResource(R.drawable.service_1);
                        break;
                    case 2:
                        imageSwitcher.setImageResource(R.drawable.service_2);
                        break;
                    case 3:
                        imageSwitcher.setImageResource(R.drawable.service_3);
                        break;
                    case 4:
                        imageSwitcher.setImageResource(R.drawable.service_4);
                        break;
                }
                animationCounter %= 5;
                if(animationCounter == 0 ) animationCounter = 1;

                imageSwitcherHandler.postDelayed(this, 4500);
            }
        });


        return view;
    }
}
