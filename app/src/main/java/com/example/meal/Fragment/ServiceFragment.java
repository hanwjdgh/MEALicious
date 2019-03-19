package com.example.meal.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meal.Activity.ServiceActivity;
import com.example.meal.R;

public class ServiceFragment extends Fragment {
    int Image[] = {R.drawable.service_1,R.drawable.service_2,R.drawable.service_3,R.drawable.service_4};
    int cnt = 0;
    ImageView imageView;
    int firsty, secondy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        imageView = view.findViewById(R.id.service);
        ImageView button = view.findViewById(R.id.next);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceActivity.mViewPager.setCurrentItem(2);
            }
        });

        imageView.setOnTouchListener(onTouchListener);

        return view;
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView image = (ImageView) v;
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    firsty = (int) event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    secondy = (int) event.getY();

                    if(firsty<secondy){
                        cnt--;
                        if(cnt==-1)
                            cnt=3;
                        image.setImageResource(Image[cnt]);
                    }
                    else{
                        cnt++;
                        if(cnt==4)
                            cnt%=4;
                        image.setImageResource(Image[cnt]);
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
            }

            return true;
        }
    };
}
