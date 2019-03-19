package com.example.meal;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal.Activity.MenuDetailActivity;
import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Activity.WalletActivity;

public class CardView extends android.support.v7.widget.CardView {
    Context context;
    RelativeLayout relativeLayout;
    RelativeLayout layout1;
    LinearLayout linearLayout;
    ImageView imageView, blankimage, imageButton;
    ImageView change, cancel;
    TextView textView;
    int cardImage, idx, height;
    String str;

    public CardView(Context context, int cardIdDrawable, int index, String mode) {
        super(context);
        this.context = context;
        this.cardImage = cardIdDrawable;
        this.idx = index;
        this.str = mode;
        init();
    }

    public CardView(Context context) {
        super(context);
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(){
        View v = inflate(getContext(),R.layout.item_flight,this);
        v.setBackgroundColor(Color.TRANSPARENT);
        linearLayout = v.findViewById(R.id.linear);
        relativeLayout = v.findViewById(R.id.real);
        imageView = v.findViewById(R.id.img);
        blankimage = v.findViewById(R.id.blank);
        imageButton = v.findViewById(R.id.order);
        textView = v.findViewById(R.id.text1);
        layout1 = v.findViewById(R.id.button_layout);
        change = v.findViewById(R.id.change);
        cancel = v.findViewById(R.id.cancel);

        linearLayout.bringToFront();
        imageView.setImageResource(this.cardImage);
        imageButton.setImageResource(R.drawable.order_b);
        change.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.INVISIBLE);

        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (str.equals("back")) {
                    if(height==50) {
                        Intent intent = new Intent(getContext(), MenuDetailActivity.class);
                        intent.putExtra("INDEX",idx);
                        TodayMenuActivity.mode = 3;
                        getContext().getApplicationContext().startActivity(intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("항공편 출발 48시간 전까지만 주문이 가능 합니다.");
                    builder.setPositiveButton("예",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.show();
                }
            }
        });


        change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(height==50) {
                    Intent intent = new Intent(getContext(), MenuDetailActivity.class);
                    intent.putExtra("INDEX",idx);
                    TodayMenuActivity.mode = 3;
                    getContext().getApplicationContext().startActivity(intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
            }
        });

        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(height==50) {
                    imageButton.setVisibility(View.VISIBLE);
                    change.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.INVISIBLE);
                    blankimage.setImageResource(R.drawable.blank);
                }
            }
        });
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setting(){
        relativeLayout.setVisibility(View.VISIBLE);
        change.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.VISIBLE);
        imageButton.setVisibility(View.GONE);
    }

    public void setString(String str){
        textView.setText(str);
    }

    public void setImage(int image){
        blankimage.setImageResource(image);
        blankimage.setScaleType(ImageView.ScaleType.FIT_XY);
        blankimage.setAdjustViewBounds(true);
    }
    public int getIndex(){
        return this.idx;
    }
}
