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

        cardsViews.add(new CardView(this, R.drawable.ticket));
        cardsViews.add(new CardView(this, R.drawable.ticket));
        cardsViews.add(new CardView(this, R.drawable.ticket));

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

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.AppBarLayout;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.ActivityOptionsCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.example.meal.Adapter.WalletAdapter;
//import com.example.meal.AppBarStateChangeListener;
//import com.example.meal.R;
//import com.example.meal.ScrollEvent;
//import com.example.meal.WalletItem;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.util.ArrayList;
//
//public class WalletActivity extends AppCompatActivity implements WalletAdapter.ItemClick {
//
//    ArrayList<WalletItem> items;
//    RecyclerView recyclerView;
////    LinearLayout headerView;
//    AppBarLayout appBarLayout;
//    TextView passportID;
//
//    AppBarStateChangeListener.State actualState;
//
//    boolean isOpen = true;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_wallet);
//
//        passportID = findViewById(R.id.passportID);
////        Intent intent = getIntent();
////        String passport = intent.getStringExtra("passport");
////        passportID.append(passport);
//        initViews();
//
//        setRecycle();
//
////        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
////            @Override
////            public void onStateChanged(AppBarLayout appBarLayout, State state) {
////                actualState = state;
////                if (state == State.IDLE) {
////                    headerView.post(new Runnable() {
////
////                        @Override
////                        public void run() {
////                            closeEffect(headerView);
////                        }
////                    });
////                } else if (state == State.EXPANDED) {
////                    headerView.post(new Runnable() {
////                        @Override
////                        public void run() {
////                            openEffect(headerView);
////                        }
////                    });
////                }
////            }
////        });
//
//        final LinearLayoutManager llayoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_SETTLING) {
//                    EventBus.getDefault().post(new ScrollEvent(0));
//                } else
//                    EventBus.getDefault().post(new ScrollEvent(1));
//
//            }
//        });
//
//    }
//
//    private void setRecycle() {
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        WalletAdapter adapter = new WalletAdapter(getItems());
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void initViews() {
////        headerView = (LinearLayout) findViewById(R.id.header);
//        recyclerView = (RecyclerView) findViewById(R.id.wallet_view);
//        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        return id == R.id.action_settings || super.onOptionsItemSelected(item);
//    }
//
//    private void openEffect(final View myView) {
//
//        if (isOpen)
//            return;
//
//        Animation bottomUp = AnimationUtils.loadAnimation(WalletActivity.this,
//                R.anim.bottom_up);
//        myView.startAnimation(bottomUp);
//        myView.setVisibility(View.VISIBLE);
//        isOpen = true;
//
//    }
//
//    private void closeEffect(final View myView) {
//        if (!isOpen)
//            return;
//
//        Animation bottomUp = AnimationUtils.loadAnimation(WalletActivity.this,
//                R.anim.bottom_up);
//        bottomUp.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                myView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//        myView.startAnimation(bottomUp);
//        myView.setVisibility(View.INVISIBLE);
//        isOpen = false;
//
//    }
//
//    @Override
//    public void onClick(View v, int position) {
//        Intent intent = new Intent(WalletActivity.this, DetailActivity.class);
//        intent.putExtra("item", items.get(position));
//        ActivityOptionsCompat options =
//
//                ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, getString(R.string.app_name));
//
//        ActivityCompat.startActivity(this, intent, options.toBundle());
//    }
//
//    public ArrayList<WalletItem> getItems() {
//        items = new ArrayList<>();
//        items.add(new WalletItem("Bottom Navigation", "#F1D05D"));
//        items.add(new WalletItem("Buttons", "#F1D05D"));
//        items.add(new WalletItem("Cards", "#00bfff"));
//        items.add(new WalletItem("Chips", "#00bfff"));
//        items.add(new WalletItem("Data Tables", "#ff33cc"));
//        items.add(new WalletItem("Dialogs", "#ff33cc"));
//        items.add(new WalletItem("Dividers", "#59D5B8"));
//        items.add(new WalletItem("Expansion Panels", "#59D5B8"));
//        items.add(new WalletItem("Grid Lists", "#F1D05D"));
//        items.add(new WalletItem("Lists", "#F1D05D"));
//        items.add(new WalletItem("Cards", "#00bfff"));
//        items.add(new WalletItem("Chips", "#00bfff"));
//        items.add(new WalletItem("Bottom Navigation", "#F1D05D"));
//        items.add(new WalletItem("Buttons", "#F1D05D"));
//        items.add(new WalletItem("Data Tables", "#ff33cc"));
//        items.add(new WalletItem("Dialogs", "#ff33cc"));
//        items.add(new WalletItem("Dividers", "#59D5B8"));
//        items.add(new WalletItem("Expansion Panels", "#59D5B8"));
//        items.add(new WalletItem("Grid Lists", "#F1D05D"));
//        items.add(new WalletItem("Lists", "#F1D05D"));
//
//        return items;
//    }
//}