package com.example.meal.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.meal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

public class TodayMenuActivity extends AppCompatActivity {
    RelativeLayout linearLayout1, relativeLayout2;
    LinearLayout relativeLayout, linearLayout;
    TextView textView,textView1,textView2, textView3;
    String start, finish, text, air;
    String[] inform;
    String[] con = new String[2];
    String[] con1 = new String[2];
    ViewGroup viewGroup, viewGroup1;
    ImageView imageView;

    int fimage;

    int check;
    public static int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_name);

        Intent intent = getIntent();
        check = Integer.parseInt(intent.getStringExtra("round"));
        text = intent.getStringExtra("text");
        air = intent.getStringExtra("air");
        start = intent.getStringExtra("start");
        finish = intent.getStringExtra("finish");
        inform = intent.getStringArrayExtra("inform");
        fimage = Integer.parseInt(intent.getStringExtra("image"));

        con[0] = inform[1];
        con[1] = inform[4];
        con1[0] = inform[4];
        con1[1] = inform[1];
//
        linearLayout1 = findViewById(R.id.card1);
        relativeLayout2 = findViewById(R.id.card2);
        relativeLayout = findViewById(R.id.rel);
        linearLayout = findViewById(R.id.card3);
        imageView = findViewById(R.id.banner);

        if(text.equals("제주항공"))
            imageView.setImageResource(R.drawable.banner_1);
        else
            imageView.setImageResource(R.drawable.banner_2);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        viewGroup = findViewById(R.id.inc);
        if(check==2) {
            LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rp.setMargins(0,150,0,0);
            linearLayout1.setLayoutParams(rp);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,300,0,0);
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            linearLayout.setLayoutParams(lp);
            relativeLayout2.setVisibility(View.GONE);

        }

        else{
            textView2 =findViewById(R.id.textView2);
            textView3 = findViewById(R.id.textView3);
            textView2.setText(finish.substring(5, 7) + "월 " + finish.substring(8, 10) + "일 "+getDateDay(finish,"yyyy-MM-dd"));
            textView3.setText(getRoute(con1));
            viewGroup1 = findViewById(R.id.inc1);

            View myLayout = getLayoutInflater().inflate(R.layout.item_view, null);
            ImageView imageView = myLayout.findViewById(R.id.image);
            imageView.setImageResource(fimage);
            TextView textView = myLayout.findViewById(R.id.airtext);
            textView.setText(text);
            TextView textView1 = myLayout.findViewById(R.id.air);
            textView1.setText(air);
            TextView st =myLayout.findViewById(R.id.starttext);
            TextView con = myLayout.findViewById(R.id.startcon);
            TextView time = myLayout.findViewById(R.id.time);
            TextView fi = myLayout.findViewById(R.id.finishtext);
            st.setText(inform[0]);
            con.setText(inform[4]);
            RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rp.setMargins(1230,40,0,0);
            time.setText(inform[2]);
            time.setLayoutParams(rp);
            fi.setText(inform[3]+"\r\n"+inform[1]);
            viewGroup1.addView((myLayout));

            viewGroup1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TodayMenuActivity.this, MenuDetailActivity.class);
                    startActivityForResult(intent,1);
                    mode = 1;
                    overridePendingTransition(0,0);
                }
            });
        }
        textView.setText(start.substring(5, 7) + "월 " + start.substring(8, 10) + "일 "+getDateDay(start,"yyyy-MM-dd"));
        textView1.setText(getRoute(con));

        View myLayout = getLayoutInflater().inflate(R.layout.item_view, null);
        ImageView imageView = myLayout.findViewById(R.id.image);
        imageView.setImageResource(fimage);
        TextView textView = myLayout.findViewById(R.id.airtext);
        textView.setText(text);
        TextView textView1 = myLayout.findViewById(R.id.air);
        textView1.setText(air);
        TextView st =myLayout.findViewById(R.id.starttext);
        TextView con = myLayout.findViewById(R.id.startcon);
        TextView time = myLayout.findViewById(R.id.time);
        TextView fi = myLayout.findViewById(R.id.finishtext);
        st.setText(inform[0]);
        con.setText(inform[1]);
        RelativeLayout.LayoutParams rp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rp.setMargins(1230,40,0,0);
        time.setText(inform[2]);
        time.setLayoutParams(rp);
        fi.setText(inform[3]+"\r\n"+inform[4]);
        viewGroup.addView((myLayout));

        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodayMenuActivity.this, MenuDetailActivity.class);
                startActivity(intent);
                mode = 0;
                overridePendingTransition(0,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) {
            finish();
            overridePendingTransition(0,0);
        }
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
                Dialog dialog = new Dialog(TodayMenuActivity.this);
                dialog.setContentView(R.layout.location_map);
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }

    public static String getDateDay(String date, String dateType){
        String day = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateType);
        Date nDate = null;
        try {
            nDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);
        int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        switch (dayNum) {
            case 1:
                day = "일요일";
                break;
            case 2:
                day = "월요일";
                break;
            case 3:
                day = "화요일";
                break;
            case 4:
                day = "수요일";
                break;
            case 5:
                day = "목요일";
                break;
            case 6:
                day = "금요일";
                break;
            case 7:
                day = "토요일";
                break;
        }
        return day;
    }

    public static String getRoute(String[] contury){
        String route = "";

        for(int i=0; i<2; i++){
            switch (contury[i]){
                case "ICN":
                    route += "인천국제공항(ICN)";
                    break;
                case "FUK":
                    route += "후쿠오카(FUK)";
                    break;
                case "JFK":
                    route += "뉴욕(JFK)";
                    break;
                case "PVG":
                    route += "상하이푸동(PVG)";
                    break;
                case "CEB":
                    route += "세부(CEB)";
                    break;
                case "VVO":
                    route += "블라디보스토크(VVO)";
                    break;
            }
            if(i==0)
                route += " → ";
//            else
//                route +="행";
        }
        return route;
    }
}
