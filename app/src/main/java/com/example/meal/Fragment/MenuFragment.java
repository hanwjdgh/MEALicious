package com.example.meal.Fragment;

import android.content.Context;
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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
    private String list[] = {"인천","샌프란시스코","도쿄","베이징","뉴욕", "다낭","마닐라","런던"};
    String inform[][] = {{"오후 3:50","ICN","1시간 30분","오후 5:20","FUK"},
            {"오전 10:00","ICN","14시간","오전 11:00","JFK"},
            {"오전 10:50","ICN","2시간 5분","오전 11:55","PVG"},
            {"오전 8:30","ICN","4시간 45분","오후 12:15","CEB"},
            {"오후 12:30","ICN","2시간 45분","오후 4:15","VVO"}};
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
                    Toast.makeText(getContext(),"출발지를 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else if(autoCompleteTextView2.getText().toString().equals("")){
                    Toast.makeText(getContext(),"도착지를 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else{
                if(appbarLayout.getTop()<0) {
                    CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appbarLayout.getLayoutParams();
                    lp.height = 600;
                    appbarLayout.setExpanded(true);
                    appbarLayout.setLayoutParams(lp);
                    imageView.setImageResource(R.drawable.search2);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    CollapsingToolbarLayout.LayoutParams ap = new CollapsingToolbarLayout.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.MATCH_PARENT);
                    ap.setMargins(350,150,0,0);
                    radioGroup.setLayoutParams(ap);
                    RadioGroup.LayoutParams rp = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    rp.setMargins(122,0,0,0);
                    radioButton.setLayoutParams(rp);
                    linearLayout.setVisibility(View.VISIBLE);
                    textView.setText("");
                }
                else {
                    linearLayout.setVisibility(View.INVISIBLE);
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
                                item[0] = new Item(R.drawable.korean, "대한항공", "(KAL)", inform[0], strdate, findate);
                                item[1] = new Item(R.drawable.delta, "델타항공", "(DAL)", inform[1], strdate, findate);
                                item[2] = new Item(R.drawable.asiana, "아시아나항공", "(AAR)", inform[2], strdate, findate);
                                item[3] = new Item(R.drawable.jejuair, "제주항공", "(7C)", inform[3], strdate, findate);
                                item[4] = new Item(R.drawable.airseoul, "에어서울항공", "(RS)", inform[4], strdate, findate);

                                for (int i = 0; i < ITEM_SIZE; i++)
                                    items.add(item[i]);

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