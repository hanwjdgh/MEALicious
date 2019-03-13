package com.example.meal.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.meal.R;

public class MainActivity extends AppCompatActivity {
    private int animationCounter = 1;
    private Handler imageSwitcherHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final com.tomer.fadingtextview.FadingTextView fadingTextView = findViewById(R.id.fading_text_view);
        fadingTextView.setTexts(new String[]{"화면을 터치해주세요      "});

        Animation in  = AnimationUtils.loadAnimation(this, R.anim.left_to_right_in);
        Animation out = AnimationUtils.loadAnimation(this, R.anim.left_to_right_out);

        final ImageSwitcher imageSwitcher = findViewById(R.id.main_image);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        ImageView button1 = findViewById(R.id.button1);
        final ImageView button2 = findViewById(R.id.button2);
        final ImageView button3 = findViewById(R.id.button3);
        final ImageView button4 = findViewById(R.id.button4);
        final ImageView button5 = findViewById(R.id.button5);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

        imageSwitcherHandler = new Handler(Looper.getMainLooper());
        imageSwitcherHandler.post(new Runnable() {
            @Override
            public void run() {
                switch (animationCounter++) {
                    case 1:
                        imageSwitcher.setImageResource(R.drawable.main_image1);
                        break;
                    case 2:
                        imageSwitcher.setImageResource(R.drawable.main_image2);
                        break;
                    case 3:
                        imageSwitcher.setImageResource(R.drawable.main_image3);
                        break;
                }
                animationCounter %= 4;
                if(animationCounter == 0 ) animationCounter = 1;

                imageSwitcherHandler.postDelayed(this, 4500);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.check_lan = 0;
                button5.setImageResource(R.drawable.korean_selected);
                button4.setImageResource(R.drawable.english);
                button3.setImageResource(R.drawable.chinese);
                button2.setImageResource(R.drawable.japanese);
                fadingTextView.setTexts(new String[]{"화면을 터치해주세요      "});
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.check_lan = 1;
                button5.setImageResource(R.drawable.korean_button);
                button4.setImageResource(R.drawable.english_selected);
                button3.setImageResource(R.drawable.chinese);
                button2.setImageResource(R.drawable.japanese);
                fadingTextView.setTexts(new String[]{"Touch the screen       "});
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.check_lan = 2;
                button5.setImageResource(R.drawable.korean_button);
                button4.setImageResource(R.drawable.english);
                button3.setImageResource(R.drawable.chinese_selected);
                button2.setImageResource(R.drawable.japanese);
                fadingTextView.setTexts(new String[]{"触摸屏幕              "});
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.check_lan = 3;
                button5.setImageResource(R.drawable.korean_button);
                button4.setImageResource(R.drawable.english);
                button3.setImageResource(R.drawable.chinese);
                button2.setImageResource(R.drawable.japanese_selected);
                fadingTextView.setTexts(new String[]{"画面をタッチしてください"});
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.location_map);
                dialog.show();
            }
        });

        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
    }
}
