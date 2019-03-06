package com.example.meal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class CardView extends android.support.v7.widget.CardView {
    Context context;
    LinearLayout linearLayout;
    int cardImage;

    public CardView(Context context, int cardIdDrawable) {
        super(context);
        this.context = context;
        this.cardImage = cardIdDrawable;
        init();
        //setImageDrawable(context.getResources().getDrawable(cardIdDrawable));
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
        linearLayout = v.findViewById(R.id.layout);
        linearLayout.setBackgroundColor(cardImage);
    }
}
