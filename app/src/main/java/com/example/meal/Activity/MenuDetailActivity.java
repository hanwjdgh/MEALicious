package com.example.meal.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meal.R;

public class MenuDetailActivity extends AppCompatActivity {
    ImageView imageView,imageView1,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.image);
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuDetailActivity.this,"메뉴 1", Toast.LENGTH_SHORT).show();
            }
        });
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuDetailActivity.this,"메뉴 2", Toast.LENGTH_SHORT).show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuDetailActivity.this,"메뉴 3", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
