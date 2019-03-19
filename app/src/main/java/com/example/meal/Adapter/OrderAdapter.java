package com.example.meal.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meal.Activity.MenuDetailActivity;
import com.example.meal.Fragment.DetailFragment;
import com.example.meal.Item;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.HorizontalViewHolder>{
    private ArrayList<MenuItem> list;
    Context context;
    int i = 0;
    public int selectedPosition  = 0;
    int index;

    public void setData(Context context, ArrayList<MenuItem> lst, int idx){
        this.context = context;
        this.list = lst;
        this.index = idx;
    }
    @Override
    public OrderAdapter.HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item1, null, false);
        OrderAdapter.HorizontalViewHolder holder = new OrderAdapter.HorizontalViewHolder(v);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final OrderAdapter.HorizontalViewHolder holder, final int position) {
        final MenuItem data = list.get(position);

        holder.mealName.setText(data.getMeal());
        holder.description.setImageResource(data.getName());
        holder.menu.setImageResource(data.getImage());
        holder.menu.getLayoutParams().height = 600;
        holder.menu.getLayoutParams().width = 800;
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DetailFragment detailFragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("TICKET",index);
                bundle.putInt("INDEX",position);
                bundle.putString("NAME",holder.mealName.getText().toString());
                bundle.putParcelable("ACTION", ((BitmapDrawable) holder.description.getDrawable()).getBitmap());
                bundle.putParcelable("IMAGE", ((BitmapDrawable) holder.menu.getDrawable()).getBitmap());
                bundle.putInt("VIDEO", data.getVideo());
                detailFragment.setArguments(bundle);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, detailFragment)
                        .addToBackStack("Payment")
                        .commit();
            }
        });

        if(selectedPosition == position)
            holder.checkcheck.setImageResource(R.drawable.checked);
        else
            holder.checkcheck.setImageResource(R.drawable.unchecked);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        ImageView description,menu,checkcheck;
        TextView mealName;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.textview);
            menu = itemView.findViewById(R.id.imageview);
            checkcheck = itemView.findViewById(R.id.checkcheck);
            mealName = itemView.findViewById(R.id.name);

            checkcheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = getLayoutPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
