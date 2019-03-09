package com.example.meal.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meal.Activity.MainActivity;
import com.example.meal.Fragment.DetailFragment;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.HorizontalViewHolder>{
    private ArrayList<MenuItem> list;
    Context context;

    public void setData(Context context, ArrayList<MenuItem> lst){
        this.context = context;
        this.list = lst;
    }
    @Override
    public OrderAdapter.HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, null, false);
        OrderAdapter.HorizontalViewHolder holder = new OrderAdapter.HorizontalViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final OrderAdapter.HorizontalViewHolder holder, int position) {
        MenuItem data = list.get(position);

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DetailFragment detailFragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("ACTION", ((BitmapDrawable) holder.description.getDrawable()).getBitmap());
                bundle.putParcelable("IMAGE", ((BitmapDrawable) holder.menu.getDrawable()).getBitmap());
                detailFragment.setArguments(bundle);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, detailFragment)
                        .addToBackStack("Payment")
                        .commit();
            }
        });
        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("주문 하실??");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

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

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        ImageView description,menu, order;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.textview);
            menu = itemView.findViewById(R.id.imageview);
            order = itemView.findViewById(R.id.order);
        }
    }
}