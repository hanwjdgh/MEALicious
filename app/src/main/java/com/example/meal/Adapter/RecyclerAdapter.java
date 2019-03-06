package com.example.meal.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Item;
import com.example.meal.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Item> items;
    int item_layout, round;
    View v;

    public RecyclerAdapter(Context context, List<Item> items, int item_layout, int chk) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
        this.round = chk;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(round==1)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null, false);
        else if(round==2)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview1, null, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Item item = items.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
        holder.image.setBackground(drawable);
        holder.textView.setText(item.getTitle());
        holder.air.setText(item.getAir());
        if(round==1) {
            holder.image1.setBackground(drawable);
            holder.textView1.setText(item.getTitle());
            holder.air1.setText(item.getAir());
        }
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TodayMenuActivity.class);
                intent.putExtra("round",String.valueOf(round));
                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, image1;
        CardView cardview;
        TextView textView, textView1, air, air1;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            image1 = itemView.findViewById(R.id.image1);
            textView = itemView.findViewById(R.id.airtext);
            textView1 = itemView.findViewById(R.id.airtext1);
            air = itemView.findViewById(R.id.air);
            air1 = itemView.findViewById(R.id.air1);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}