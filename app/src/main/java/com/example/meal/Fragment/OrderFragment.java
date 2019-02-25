package com.example.meal.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meal.R;

public class OrderFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        Button agree = view.findViewById(R.id.agree);
        Button lookup = view.findViewById(R.id.lookup);

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.personal_information);
                dialog.show();
            }
        });

        lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// 지갑 화면 켜지기

            }
        });
        return view;
    }
}
