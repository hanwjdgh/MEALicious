package com.example.meal.Activity;

import com.example.meal.CardView;
import com.example.meal.CardWalletView;
import com.example.meal.R;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity {

    private CardWalletView mCardWalletView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        List<CardView> cardsViews = new ArrayList<>();

        cardsViews.add(new CardView(this, R.drawable.korean));
        cardsViews.add(new CardView(this, R.drawable.delta));
        cardsViews.add(new CardView(this, R.drawable.asiana));
        cardsViews.add(new CardView(this, R.drawable.jeju));
        mCardWalletView = new CardWalletView(this, cardsViews);
        ((RelativeLayout) findViewById(R.id.activity_layout)).addView(mCardWalletView);
    }

    @Override
    public void onBackPressed() {
        if (mCardWalletView.isPresentingCards()) {
            mCardWalletView.exitPresentingCardMode();
        } else {
            super.onBackPressed();
            overridePendingTransition(0,0);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        menu.getItem(0).getSubMenu().getItem(ServiceActivity.check_lan).setChecked(true);
        menu.getItem(0).setTitle(ServiceActivity.lan[ServiceActivity.check_lan]);
        for(int i=0; i<4; i++)
            menu.getItem(0).getSubMenu().getItem(i).setTitle(ServiceActivity.menu_title[ServiceActivity.check_lan][i]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(homeIntent);
                return true;
            case R.id.lag:
                return true;
            case R.id.kor:
                ServiceActivity.check_lan = 0;
                invalidateOptionsMenu();
                return true;
            case R.id.eng:
                ServiceActivity.check_lan = 1;
                invalidateOptionsMenu();
                return true;
            case R.id.cin:
                ServiceActivity.check_lan = 2;
                invalidateOptionsMenu();
                return true;
            case R.id.jap:
                ServiceActivity.check_lan = 3;
                invalidateOptionsMenu();
                return true;
            case R.id.map:
                Dialog dialog = new Dialog(WalletActivity.this);
                dialog.setContentView(R.layout.location_map);
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
