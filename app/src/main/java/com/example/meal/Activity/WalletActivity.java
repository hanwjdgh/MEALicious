package com.example.meal.Activity;

import com.example.meal.CardView;
import com.example.meal.CardWalletView;
import com.example.meal.R;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class WalletActivity extends AppCompatActivity {
    int fimage[] = {R.drawable.asiana_t,R.drawable.korean_t,R.drawable.delta_t,R.drawable.jeju_t};
    int simage[] = {R.drawable.asiana_t1,R.drawable.korean_t1,R.drawable.delta_t1,R.drawable.jeju_t1};
    int idx,id;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        if(ServiceActivity.cardsViews.size()==0) {
            idx = (int) (Math.random() * 4);
            while (true) {
                id = (int) (Math.random() * 4);
                if (id != idx)
                    break;
            }
            ServiceActivity.cardsViews.add(new CardView(this, simage[idx], 0,"back"));
            ServiceActivity.cardsViews.add(new CardView(this, simage[id], 1,"back"));
            ServiceActivity.cardsViews.add(new CardView(this, fimage[id], 2,"go"));
            ServiceActivity.cardsViews.add(new CardView(this, fimage[idx], 3,"go"));
            ServiceActivity.mCardWalletView = new CardWalletView(this, ServiceActivity.cardsViews);
        }

        relativeLayout = findViewById(R.id.activity_layout);
        relativeLayout.addView(ServiceActivity.mCardWalletView);
    }

    @Override
    public void onBackPressed() {
        if (ServiceActivity.mCardWalletView.isPresentingCards()) {
            ServiceActivity.mCardWalletView.exitPresentingCardMode();
        } else {
            super.onBackPressed();
            ((ViewGroup) ServiceActivity.mCardWalletView.getParent()).removeView(ServiceActivity.mCardWalletView);
            overridePendingTransition(0,0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        menu.getItem(0).getSubMenu().getItem(ServiceActivity.check_lan).setChecked(true);
        menu.getItem(0).setTitle("language");
        for(int i=0; i<4; i++)
            menu.getItem(0).getSubMenu().getItem(i).setTitle(ServiceActivity.menu_title[i]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                ServiceActivity.cardsViews.clear();
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
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
