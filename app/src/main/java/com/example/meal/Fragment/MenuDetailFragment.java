package com.example.meal.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meal.Activity.MenuDetailActivity;
import com.example.meal.Activity.ServiceActivity;
import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Adapter.DetailAdapter;
import com.example.meal.Adapter.OrderAdapter;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class MenuDetailFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Button button;

    int menuname[] = {R.drawable.menu_1, R.drawable.menu_2, R.drawable.menu_3};
    int Images[] = {R.drawable.meal_1, R.drawable.meal_2, R.drawable.meal_3};
    String meals[] = {"불고기 덮밥", "카레 덮밥", "비빔밥"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        if (TodayMenuActivity.mode == 1 || TodayMenuActivity.mode == 3) {
            view = inflater.inflate(R.layout.fragment_menudetail2, container, false);
            if (TodayMenuActivity.mode == 1) {
                button = view.findViewById(R.id.obutton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceActivity.mViewPager.setCurrentItem(2);
                        getActivity().finish();
                    }
                });
            }
            else{
                button = view.findViewById(R.id.obutton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("주문 하실??");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        ((MenuDetailActivity)v.getContext()).finish();
                                        ((MenuDetailActivity)v.getContext()).overridePendingTransition(0,0);
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        builder.show();
                    }
                });
            }
        } else {
            view = inflater.inflate(R.layout.fragment_menudetail, container, false);
        }

        recyclerView = view.findViewById(R.id.listView);

        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        if (TodayMenuActivity.mode != 3) {
            DetailAdapter mAdapter = new DetailAdapter();

            ArrayList<MenuItem> items = new ArrayList<>();
            for (int i = 0; i < 3; i++)
                items.add(new MenuItem(menuname[i], Images[i], meals[i]));


            mAdapter.setData(getActivity().getApplicationContext(), items);
            recyclerView.setAdapter(mAdapter);
        } else {
            OrderAdapter mAdapter = new OrderAdapter();

            ArrayList<MenuItem> items = new ArrayList<>();
            for (int i = 0; i < 3; i++)
                items.add(new MenuItem(menuname[i], Images[i],meals[i]));


            mAdapter.setData(getActivity().getApplicationContext(), items);
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }
}