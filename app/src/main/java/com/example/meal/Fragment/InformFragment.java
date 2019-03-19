package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.meal.R;

public class InformFragment extends Fragment {
    int inform_image[]={R.drawable.inform_tab,R.drawable.inform_tab1,R.drawable.inform_tab1};
    ImageView inform;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_about, container, false);

        Bundle bundle = getArguments();
        int index= bundle.getInt("INDEX");
        FrameLayout.LayoutParams rp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rp.setMargins(15,100,15,0);
        inform = view.findViewById(R.id.about);
        inform.setImageResource(inform_image[index]);
        inform.setLayoutParams(rp);

        return view;
    }
}
