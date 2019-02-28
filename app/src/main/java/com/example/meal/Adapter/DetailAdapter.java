package com.example.meal.Adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meal.Fragment.DetailFragment;
import com.example.meal.MenuItem;
import com.example.meal.R;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.HorizontalViewHolder> {
    private ArrayList<MenuItem> list;
    Context context;

    public void setData(Context context, ArrayList<MenuItem> lst){
        this.context = context;
        this.list = lst;
    }
    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, false);
        HorizontalViewHolder holder = new HorizontalViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HorizontalViewHolder holder, int position) {
        MenuItem data = list.get(position);

        holder.description.setText(data.getName());
        holder.icon.setImageResource(data.getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                DetailFragment detailFragment = new DetailFragment();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    detailFragment.setSharedElementEnterTransition(TransitionInflater.from(activity).inflateTransition(R.transition.change_image_trans));
//                    detailFragment.setEnterTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.fade));
//                }
                Bundle bundle = new Bundle();
                bundle.putString("ACTION", holder.description.getText().toString());
                bundle.putParcelable("IMAGE", ((BitmapDrawable) holder.icon.getDrawable()).getBitmap());
                detailFragment.setArguments(bundle);
                FragmentManager fragmentManager = activity.getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, detailFragment)
                        .addToBackStack("Payment")
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView description;
        CardView cardView;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.imageview);
            description = itemView.findViewById(R.id.textview);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
