package com.example.meal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.example.meal.R;

public class TodayMenuActivity extends AppCompatActivity {
    View view, view1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);

        view = findViewById(R.id.inc);
        view1 = findViewById(R.id.inc1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayMenuActivity.this, MenuDetailActivity.class);
                startActivity(intent);
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
}
