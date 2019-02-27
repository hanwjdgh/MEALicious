package com.example.meal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.meal.R;

public class WalletActivity extends AppCompatActivity {

    TextView passportID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        passportID = findViewById(R.id.passportID);
        Intent intent = getIntent();
        String passport = intent.getStringExtra("passport");
        passportID.setText(passport);
    }
}
