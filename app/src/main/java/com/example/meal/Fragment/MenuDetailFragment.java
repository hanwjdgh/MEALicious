package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.meal.Adapter.DetailAdapter;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class MenuDetailFragment extends Fragment{
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    String menuname[] = {"메뉴1","메뉴2","메뉴3"};
    int Images[] = {R.drawable.meal_1,R.drawable.meal_2,R.drawable.meal_3};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menudetail, container, false);
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