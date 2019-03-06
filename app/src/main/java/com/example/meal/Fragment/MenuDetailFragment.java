package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meal.Activity.ServiceActivity;
import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Adapter.DetailAdapter;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class MenuDetailFragment extends Fragment{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Button button;

    String menuname[] = {"메뉴 1","메뉴 2","메뉴 3"};
    int Images[] = {R.drawable.meal_1,R.drawable.meal_2,R.drawable.meal_3};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        if(TodayMenuActivity.mode == 1) {
            view = inflater.inflate(R.layout.fragment_menudetail2, container, false);
            button = view.findViewById(R.id.obutton);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServiceActivity.mViewPager.setCurrentItem(2);
                    getActivity().finish();
                }
            });
        }else{
            view = inflater.inflate(R.layout.fragment_menudetail, container, false);
        }

        recyclerView = view.findViewById(R.id.listView);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        DetailAdapter mAdapter = new DetailAdapter();

        ArrayList<MenuItem> items = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            items.add(new MenuItem(menuname[i], Images[i]));



        mAdapter.setData(getActivity().getApplicationContext(),items);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}