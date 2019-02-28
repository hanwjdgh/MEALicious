package com.example.meal.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.meal.Fragment.MenuDetailFragment;
import com.example.meal.R;

public class MenuDetailActivity extends ServiceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        MenuDetailFragment menuDetailFragment = new MenuDetailFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, menuDetailFragment).commit();

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
