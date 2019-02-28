package com.example.meal.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Activity.WalletActivity;
import com.example.meal.R;

public class OrderFragment extends Fragment {
    InputMethodManager imm;
    View v;
    ImageView confirm;
    final Dialog dialog = null;
    int i = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        v = view.findViewById(R.id.background);

        ImageView agree = view.findViewById(R.id.agree);
        final ImageView check = view.findViewById(R.id.check);
        ImageView goNext = view.findViewById(R.id.search);
        final EditText editText = view.findViewById(R.id.passport_id_edit);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1 - i;

                if (i == 0) {
                    check.setImageResource(R.drawable.unchecked);
                } else {
                    check.setImageResource(R.drawable.checked);
                }
            }
        });

        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.personal_information);
                confirm = dialog.findViewById(R.id.confirm);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 테두리 지움
                dialog.show();
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

//        RadioButton.OnClickListener optionOnClickListener
//                = new RadioButton.OnClickListener() {
//
//            public void onClick(View v) {
//
//            }
//        };
//        option1.setOnClickListener(optionOnClickListener);
//        option2.setOnClickListener(optionOnClickListener);

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Toast.makeText(getContext(), "개인 정보 및 수집 이용에 동의해주셔야 합니다", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), WalletActivity.class);

//                    intent.putExtra("passport",editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
