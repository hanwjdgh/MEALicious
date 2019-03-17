package com.example.meal;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CardView extends android.support.v7.widget.CardView {
    Context context;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    ImageView imageView, blankimage, imageButton;
    TextView textView;
    int cardImage;

    public CardView(Context context, int cardIdDrawable) {
        super(context);
        this.context = context;
        this.cardImage = cardIdDrawable;
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

        linearLayout.bringToFront();
        imageView.setImageResource(this.cardImage);
        imageButton.setImageResource(R.drawable.order_b);
        relativeLayout.setVisibility(View.INVISIBLE);
    }

    public void setting(){
        relativeLayout.setVisibility(View.VISIBLE);
    }

    public void setString(String str){
        textView.setText(str);
    }

    public void setImage(int image){
        blankimage.setImageResource(image);
        blankimage.setScaleType(ImageView.ScaleType.FIT_XY);
        blankimage.setAdjustViewBounds(true);
    }
}
