package com.example.meal.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    MenuItem item;
    int menuname[] = {R.drawable.menu_1, R.drawable.menu_2, R.drawable.menu_3};
    int Images[] = {R.drawable.meal_1, R.drawable.meal_2, R.drawable.meal_3};
    int videos[] = {R.raw.roast, R.raw.slice, R.raw.roast};
    String meals[] = {"불고기 덮밥", "낙지 덮밥", "생선 크림 파스타"};
    DetailAdapter detailAdapter;
    OrderAdapter orderAdapter;
    ImageView confirm, cancel, check_order_image;
    TextView check_order_name;

    ArrayList<MenuItem> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        final ArrayList<MenuItem> items = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            items.add(new MenuItem(menuname[i], Images[i], meals[i], videos[i]));

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
            } else {
                button = view.findViewById(R.id.obutton);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        final Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.check_order);
                        if (orderAdapter.selectedPosition != -1) {
                            item = items.get(orderAdapter.selectedPosition);
                        }
                        confirm = dialog.findViewById(R.id.confirm);
                        cancel = dialog.findViewById(R.id.cancel);
                        check_order_name = dialog.findViewById(R.id.check_order_name);
                        check_order_name.setText(item.getMeal());
                        check_order_image = dialog.findViewById(R.id.check_order_image);
                        check_order_image.setImageResource(item.getImage());

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 테두리 지움
                        dialog.show();

                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ((MenuDetailActivity) getContext()).finish();
                                ((MenuDetailActivity) getContext()).overridePendingTransition(0, 0);
                                Toast.makeText(getContext(), item.getMeal(), Toast.LENGTH_SHORT).show();

                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });

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
            detailAdapter = new DetailAdapter();

            detailAdapter.setData(getActivity().getApplicationContext(), items);
            recyclerView.setAdapter(detailAdapter);
        } else {
            orderAdapter = new OrderAdapter();
            orderAdapter.setData(getActivity().getApplicationContext(), items);
            recyclerView.setAdapter(orderAdapter);
        }
        return view;
    }
}