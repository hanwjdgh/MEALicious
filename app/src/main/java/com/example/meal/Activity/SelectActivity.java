package com.example.meal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.meal.R;

public class SelectActivity extends AppCompatActivity {
    ImageButton button1,button2,button3,button4;
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

        button1.setOnTouchListener(onTouchListener);
        button2.setOnTouchListener(onTouchListener);
        button3.setOnTouchListener(onTouchListener);
        button4.setOnTouchListener(onTouchListener);

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
                s_num = 3;
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageButton imageButton = (ImageButton) v;
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    imageButton.setAlpha((float) (0.5));
                    break;
                case MotionEvent.ACTION_UP:
                    imageButton.setAlpha((float) 1.0);
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
