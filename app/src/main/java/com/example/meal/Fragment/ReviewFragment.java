package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.meal.R;

public class ReviewFragment extends Fragment {
    int review_image[]={R.drawable.review_tab,R.drawable.review_tab1,R.drawable.review_tab2};
    ImageView review;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_review, container, false);

        Bundle bundle = getArguments();
        int index= bundle.getInt("INDEX");
        FrameLayout.LayoutParams rp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rp.setMargins(60,0,60,0);
        review = view.findViewById(R.id.review);
        review.setImageResource(review_image[index]);
        review.setLayoutParams(rp);

        return view;
    }
}
