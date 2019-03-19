package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meal.R;

public class AboutFragment extends Fragment {
    int about_image[]={R.drawable.about_tab,R.drawable.about_tab1,R.drawable.about_tab1};
    ImageView about;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_about, container, false);

        Bundle bundle = getArguments();
        int index= bundle.getInt("INDEX");
        about = view.findViewById(R.id.about);
        about.setImageResource(about_image[index]);

        return view;
    }
}
