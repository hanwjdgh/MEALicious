package com.example.meal.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal.Adapter.RecyclerAdapter;
import com.example.meal.Item;
import com.example.meal.R;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private String list[] = {"인천","후쿠오카","상하이푸동","세부","뉴욕", "블라디보스토크"};
    String inform[][] = {{"오후 3:50","ICN","1시간 30분","오후 5:20","FUK","후쿠오카"},
            {"오전 10:00","ICN","1시간 30분","오전 11:30","FUK","후쿠오카"},
            {"오전 10:25","ICN","1시간 30분","오전 11:55","FUK","후쿠오카"},
            {"오전 9:00","ICN","14시간","오전 11:00","JFK","뉴욕"},
            {"오전 12:00","ICN","14시간","오후 2:00","JFK","뉴욕"},
            {"오전 10:50","ICN","2시간 5분","오전 11:55","PVG","상하이푸동"},
            {"오전 8:30","ICN","4시간 45분","오후 12:15","CEB","세부"},
            {"오후 12:30","ICN","2시간 45분","오후 4:15","VVO","블라디보스토크"}
    };
    FloatingActionButton floatingActionButton;
    AppBarLayout appbarLayout;
    TextView textView, dateView, dateView2;
    AutoCompleteTextView autoCompleteTextView1,autoCompleteTextView2;
    ImageView imageView,linearLayout;
    InputMethodManager imm;
    RecyclerView recyclerView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    int round = 1;
    final int ITEM_SIZE = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        floatingActionButton = view.findViewById(R.id.fab);
        appbarLayout = view.findViewById(R.id.appbar);
        textView = view.findViewById(R.id.title);
        dateView = view.findViewById(R.id.date_view);
        dateView2 = view.findViewById(R.id.date_view2);
        imageView = view.findViewById(R.id.imageView1);
        autoCompleteTextView1 = view.findViewById(R.id.search);
        autoCompleteTextView2 = view.findViewById(R.id.search2);
        recyclerView = view.findViewById(R.id.main_rv);
        radioGroup = view.findViewById(R.id.radio);
        radioButton = view.findViewById(R.id.option2);
        linearLayout = view.findViewById(R.id.border);
        collapsingToolbarLayout = view. findViewById(R.id.collapsingToolbarLayout01);
        toolbar = view.findViewById(R.id.toolbar);

        linearLayout.bringToFront();
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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(autoCompleteTextView1.getWindowToken(), 0);
            }
        });

        AndroidThreeTen.init(getContext());
        LocalDate date = LocalDate.now();
        int day1 = date.getDayOfMonth();
        final LocalDate localDate1 = LocalDate.of(date.getYear(), date.getMonthValue(), day1);

        dateView.setText(localDate1.format(DateTimeFormatter.ISO_LOCAL_DATE));
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
            }
        });

        final LocalDate localDate2 = date.plusDays(3);
        dateView2.setText(localDate2.format(DateTimeFormatter.ISO_LOCAL_DATE));
        dateView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "DatePicker2");
            }
        });

        RadioGroup.OnCheckedChangeListener rl = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.option1){
                    dateView2.setText(localDate2.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    dateView2.setClickable(true);
                    round = 1;
                }
                else {
                    dateView2.setText("");
                    dateView2.setClickable(false);
                    round = 2;
                }
            }
        };
        radioGroup.setOnCheckedChangeListener(rl);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoCompleteTextView1.getText().toString().equals("")){
                    Toast.makeText(getContext(),"출발지를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(autoCompleteTextView2.getText().toString().equals("")){
                    Toast.makeText(getContext(),"도착지를 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(appbarLayout.getTop()<0) {
                        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appbarLayout.getLayoutParams();

                        toolbar.setBackgroundColor(Color.parseColor("#00000000"));
                        lp.height = 600;
                        appbarLayout.setExpanded(true);
                        appbarLayout.setLayoutParams(lp);
                        imageView.setImageResource(R.drawable.search2);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        CollapsingToolbarLayout.LayoutParams ap = new CollapsingToolbarLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.MATCH_PARENT);
                        ap.setMargins(350, 150, 0, 0);
                        radioGroup.setLayoutParams(ap);
                        RadioGroup.LayoutParams rp = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        rp.setMargins(122, 0, 0, 0);
                        radioButton.setLayoutParams(rp);
                        ap =  new CollapsingToolbarLayout.LayoutParams(250, ViewGroup.LayoutParams.WRAP_CONTENT);
                        ap.setMargins(575,280,0,0);
                        autoCompleteTextView1.setLayoutParams(ap);
                        ap =  new CollapsingToolbarLayout.LayoutParams(250, ViewGroup.LayoutParams.WRAP_CONTENT);
                        ap.setMargins(1175,280,0,0);
                        autoCompleteTextView2.setLayoutParams(ap);
                        ap =  new CollapsingToolbarLayout.LayoutParams(200, 49);
                        ap.setMargins(610,420,0,0);
                        dateView.setLayoutParams(ap);
                        ap =  new CollapsingToolbarLayout.LayoutParams(200, 49);
                        ap.setMargins(1210,420,0,0);
                        dateView2.setLayoutParams(ap);

                        linearLayout.setVisibility(View.VISIBLE);
                        textView.setText("");
                    }
                    else {
                        linearLayout.setVisibility(View.INVISIBLE);
                        toolbar.setBackgroundColor(Color.parseColor("#A5A2A2"));
                        String str = autoCompleteTextView1.getText().toString();
                        String str2 = autoCompleteTextView2.getText().toString();
                        String str3 = dateView.getText().toString();
                        String str4 = dateView2.getText().toString();
                        String strdate = str3;
                        String findate = str4;

                        appbarLayout.setExpanded(false);
                        if (str.length() > 0 && str2.length() > 0) {
                            if (str4.equals("")) {
                                textView.setText(str + " - " + str2 + "   " + str3.substring(5, 7) + "월 " + str3.substring(8, 10) + "일");

                            } else {
                                textView.setText(str + " - " + str2 + "   " + str3.substring(5, 7) + "월 " + str3.substring(8, 10) + "일" + " ~ " + str4.substring(5, 7) + "월 " + str4.substring(8, 10) + "일");
                            }

                            if (round != 0) {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                                layoutManager.setOrientation(LinearLayout.VERTICAL);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(layoutManager);

                                List<Item> items = new ArrayList<>();
                                Item[] item = new Item[ITEM_SIZE];
                                int airImage[] ={R.drawable.korean,R.drawable.delta,R.drawable.asiana,R.drawable.jejuair,R.drawable.airseoul};
                                String airName[] = {"대한항공","델타항공","아시아나항공","제주항공","에어서울항공"};
                                String airMark[] ={"(KAL)","(DAL)","(AAR)","(7C)","(RS)"};
                                String temp = autoCompleteTextView2.getText().toString();

                                for(int i=0; i<inform.length; i++){
                                    if(temp.equals(inform[i][5])) {
                                        int random = (int) (Math.random() * 5);
                                        items.add(new Item(airImage[random], airName[random], airMark[random], inform[i], strdate, findate));
                                    }
                                }

                                recyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(), items, R.layout.fragment_menu, round));
                            }
                        }

                    }
                }
            }
        });

        return view;
    }
}