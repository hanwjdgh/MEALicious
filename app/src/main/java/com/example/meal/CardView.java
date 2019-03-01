package com.example.meal;
import android.content.Context;
import android.util.AttributeSet;


public class CardView extends android.support.v7.widget.AppCompatImageView {

    public CardView(Context context, int cardIdDrawable) {
        super(context);
        setImageDrawable(context.getResources().getDrawable(cardIdDrawable));
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
}
