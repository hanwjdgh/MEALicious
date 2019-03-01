package com.example.meal.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.meal.Fragment.MenuDetailFragment;
import com.example.meal.R;

public class MenuDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

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
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        menu.getItem(0).getSubMenu().getItem(ServiceActivity.check_lan).setChecked(true);
        menu.getItem(0).setTitle(ServiceActivity.lan[ServiceActivity.check_lan]);
        for(int i=0; i<4; i++)
            menu.getItem(0).getSubMenu().getItem(i).setTitle(ServiceActivity.menu_title[ServiceActivity.check_lan][i]);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()){
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
                return true;
            case R.id.lag:
                return true;
            case R.id.kor:
                ServiceActivity.check_lan = 0;
                Toast.makeText(this, "한국어", Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                return true;
            case R.id.eng:
                ServiceActivity.check_lan = 1;
                Toast.makeText(this, "영어", Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                return true;
            case R.id.cin:
                ServiceActivity.check_lan = 2;
                Toast.makeText(this, "중국어", Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                return true;
            case R.id.jap:
                ServiceActivity.check_lan = 3;
                Toast.makeText(this, "일어", Toast.LENGTH_SHORT).show();
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
}
