package com.example.meal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.meal.R;

public class TodayMenuActivity extends ServiceActivity {
    View view, view1;
    LinearLayout linearLayout1, linearLayout2;
    RelativeLayout relativeLayout;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);

        Intent intent = getIntent();
        check = Integer.parseInt(intent.getStringExtra("round"));

        linearLayout1 = findViewById(R.id.card1);
        linearLayout2 = findViewById(R.id.card2);
        relativeLayout = findViewById(R.id.rel);

        if(check==2) {
            relativeLayout.setGravity(Gravity.CENTER_VERTICAL);
            relativeLayout.setPadding(0,0,0,200);
            linearLayout2.setVisibility(View.GONE);
        }
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
