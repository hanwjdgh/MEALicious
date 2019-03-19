package com.example.meal.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.meal.Fragment.MenuDetailFragment;
import com.example.meal.R;

public class MenuDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        int idx = getIntent().getIntExtra("INDEX",0);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        MenuDetailFragment menuDetailFragment = new MenuDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX",idx);
        menuDetailFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, menuDetailFragment).commit();

    }

    @Override
    public void onBackPressed(){
        setResult(1);
        super.onBackPressed();
        overridePendingTransition(0,0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        menu.getItem(0).getSubMenu().getItem(ServiceActivity.check_lan).setChecked(true);
        menu.getItem(0).setTitle("language");
        for(int i=0; i<4; i++)
            menu.getItem(0).getSubMenu().getItem(i).setTitle(ServiceActivity.menu_title[i]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);;
                startActivity(homeIntent);
                return true;
            case R.id.lag:
                return true;
            case R.id.kor:
                ServiceActivity.check_lan = 0;
                invalidateOptionsMenu();
                return true;
            case R.id.eng:
                ServiceActivity.check_lan = 1;
                invalidateOptionsMenu();
                return true;
            case R.id.cin:
                ServiceActivity.check_lan = 2;
                invalidateOptionsMenu();
                return true;
            case R.id.jap:
                ServiceActivity.check_lan = 3;
                invalidateOptionsMenu();
                return true;
            case R.id.map:
                Dialog dialog = new Dialog(MenuDetailActivity.this);
                dialog.setContentView(R.layout.location_map);
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }
    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
