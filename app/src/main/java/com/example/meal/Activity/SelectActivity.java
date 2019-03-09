package com.example.meal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.meal.R;

public class SelectActivity extends AppCompatActivity {
    Button button1,button2,button3,button4;
    public static int s_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_num = 0;
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_num = 1;
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_num = 2;
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_num = 4;
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
