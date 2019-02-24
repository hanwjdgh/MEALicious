package com.example.meal.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.meal.Adapter.RecyclerAdapter;
import com.example.meal.Item;
import com.example.meal.R;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    private String list[] = {"한국","미국","일본","중국","러시아", "호주","필리핀","영국"};
    FloatingActionButton floatingActionButton;
    AppBarLayout appbarLayout;
    TextView textView, dateView, dateView2;
    AutoCompleteTextView autoCompleteTextView1,autoCompleteTextView2;
    ImageView imageView;
    InputMethodManager imm;
    RecyclerView recyclerView;

    final int ITEM_SIZE = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        floatingActionButton = view.findViewById(R.id.fab);
        appbarLayout = view.findViewById(R.id.appbar);
        textView = view.findViewById(R.id.title);
        dateView = view.findViewById(R.id.date_view);
        dateView2 = view.findViewById(R.id.date_view2);
        imageView = view.findViewById(R.id.imageView1);
        autoCompleteTextView1 = view.findViewById(R.id.search);
        autoCompleteTextView2 = view.findViewById(R.id.search2);
        recyclerView = view.findViewById(R.id.main_rv);
        appbarLayout.bringToFront();

        if (appbarLayout.getLayoutParams() != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appbarLayout.getLayoutParams();
            AppBarLayout.Behavior appBarLayoutBehaviour = new AppBarLayout.Behavior();
            appBarLayoutBehaviour.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
                @Override
                public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                    return false;
                }
            });
            layoutParams.setBehavior(appBarLayoutBehaviour);
        }

        autoCompleteTextView1.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_dropdown_item_1line,  list ));
        autoCompleteTextView2.setAdapter(new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_dropdown_item_1line,  list ));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(autoCompleteTextView1.getWindowToken(), 0);
            }
        });

        AndroidThreeTen.init(getContext());
        LocalDate date = LocalDate.now();
        int day1 = date.getDayOfMonth()-1;
        final LocalDate localDate1 = LocalDate.of(date.getYear(), date.getMonthValue(), day1);

        dateView.setText(localDate1.format(DateTimeFormatter.ISO_LOCAL_DATE));
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
            }
        });

        int day2 = date.getDayOfMonth()+2;
        final LocalDate localDate2 = LocalDate.of(date.getYear(), date.getMonthValue(), day2);
        dateView2.setText(localDate2.format(DateTimeFormatter.ISO_LOCAL_DATE));
        dateView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "DatePicker2");
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
                        textView.setText(str+" - "+str2+"  "+localDate1.getMonthValue()+"월"+localDate1.getDayOfMonth()+"일"+ " ~ "+localDate2.getMonthValue()+"월"+localDate2.getDayOfMonth()+"일");
                    appbarLayout.setExpanded(false);

                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    List<Item> items = new ArrayList<>();
                    Item[] item = new Item[ITEM_SIZE];
                    item[0] = new Item(R.drawable.airport, "#1");
                    item[1] = new Item(R.drawable.airport, "#2");
                    item[2] = new Item(R.drawable.airport, "#3");
                    item[3] = new Item(R.drawable.airport, "#4");
                    item[4] = new Item(R.drawable.airport, "#5");

                    for (int i = 0; i < ITEM_SIZE; i++) {
                        items.add(item[i]);
                    }

                    recyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), items, R.layout.fragment_order));

                }
            }
        });

        return view;
    }
}
