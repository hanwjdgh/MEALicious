package com.example.meal.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal.Activity.ServiceActivity;
import com.example.meal.R;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private String list[] = {"한국","미국","일본","중국","러시아", "호주","필리핀","영국"};
    FloatingActionButton floatingActionButton;
    AppBarLayout appbarLayout;
    TextView textView;
    AutoCompleteTextView autoCompleteTextView1,autoCompleteTextView2;
    ImageView imageView;
    InputMethodManager imm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        floatingActionButton = view.findViewById(R.id.fab);
        appbarLayout = view.findViewById(R.id.appbar);
        textView = view.findViewById(R.id.title);
        imageView = view.findViewById(R.id.imageView1);
        autoCompleteTextView1 = view.findViewById(R.id.search);
        autoCompleteTextView2 = view.findViewById(R.id.search2);

        autoCompleteTextView1.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_dropdown_item_1line,  list ));
        autoCompleteTextView2.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_dropdown_item_1line,  list ));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(autoCompleteTextView1.getWindowToken(), 0);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(appbarLayout.getTop()<0) {
                    appbarLayout.setExpanded(true);
                    textView.setText("기내식 검색");
                }
                else {
                    String str = autoCompleteTextView1.getText().toString();
                    String str2 = autoCompleteTextView2.getText().toString();
                    if(str.length() > 0 && str2.length() > 0)
                        textView.setText(str+" -> "+str2);
                    appbarLayout.setExpanded(false);
                }
            }
        });
        return view;
    }
}
