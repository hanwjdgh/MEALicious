package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.meal.R;

public class AboutFragment extends Fragment {
    int about_image[]={R.drawable.about_tab,R.drawable.about_tab1,R.drawable.about_tab2};
    ImageView about;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_about, container, false);

        Bundle bundle = getArguments();
        int index= bundle.getInt("INDEX");
        FrameLayout.LayoutParams rp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rp.setMargins(60,0,60,0);
        about = view.findViewById(R.id.about);
        about.setImageResource(about_image[index]);
        about.setLayoutParams(rp);

        return view;
    }
}
