package com.example.meal.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.meal.Activity.TodayMenuActivity;
import com.example.meal.Activity.WalletActivity;
import com.example.meal.R;

public class OrderFragment extends Fragment {
    InputMethodManager imm;
    EditText editText;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        v = view.findViewById(R.id.background);
        editText = view.findViewById(R.id.passport);
        Button agree = view.findViewById(R.id.agree);
        Button lookup = view.findViewById(R.id.lookup);
        final RadioButton option1 = view.findViewById(R.id.option1);
        final RadioButton option2 = view.findViewById(R.id.option2);

        option2.setChecked(true);


        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.personal_information);
                dialog.show();
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });

        RadioButton.OnClickListener optionOnClickListener
                = new RadioButton.OnClickListener() {

            public void onClick(View v) {

            }
        };
        option1.setOnClickListener(optionOnClickListener);
        option2.setOnClickListener(optionOnClickListener);

        lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(option2.isChecked()) {
                    Toast.makeText(getContext(), "동의해주셔야 합니다", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), WalletActivity.class);
                    intent.putExtra("passport",editText.getText().toString());
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
