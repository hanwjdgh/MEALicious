package com.example.meal.Activity;

import com.example.meal.CardView;
import com.example.meal.CardWalletView;
import com.example.meal.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity {

    private CardWalletView mCardWalletView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        List<CardView> cardsViews = new ArrayList<>();

        cardsViews.add(new CardView(this, R.drawable.trip_1));
        cardsViews.add(new CardView(this, R.drawable.trip_2));
//        cardsViews.add(new CardView(this, R.drawable.ticket));

        mCardWalletView = new CardWalletView(this, cardsViews);
        ((RelativeLayout) findViewById(R.id.activity_layout)).addView(mCardWalletView);
    }

    @Override
    public void onBackPressed() {
        if (mCardWalletView.isPresentingCards()) {
            mCardWalletView.exitPresentingCardMode();
        } else {
            super.onBackPressed();
        }
    }
}
