package com.example.meal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.meal.R;

public class TodayMenuActivity extends ServiceActivity {
    View view, view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        view = findViewById(R.id.inc);
        view1 = findViewById(R.id.inc1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayMenuActivity.this, MenuDetailActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayMenuActivity.this, MenuDetailActivity.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.obutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                ServiceActivity.mViewPager.setCurrentItem(2);
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(0,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
