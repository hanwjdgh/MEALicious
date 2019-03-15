package com.example.meal.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

@SuppressLint({"NewApi", "LocalSuppress"})
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    int year, month, day, d = 0, e = 0, f = 0;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LocalDate date = LocalDate.now();
        year = date.getYear();
        month = date.getMonthValue() - 1;
        day = date.getDayOfMonth();

        LocalDate date2 = date.plusDays(3);
        int year2 = date2.getYear();
        int month2 = date2.getMonthValue() - 1;
        int day2 = date2.getDayOfMonth();

        if (getTag().equals("DatePicker")) {
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }
        return new DatePickerDialog(getActivity(), this, year2, month2, day2);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        LocalDate localDate = LocalDate.of(year, month + 1, dayOfMonth);

        if (getTag().equals("DatePicker")) {

            ((TextView) getActivity().findViewById(R.id.date_view)).setText(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            d = localDate.getYear();
            e = localDate.getMonthValue() - 1;
            f = localDate.getDayOfMonth();

            if (year > d || month > e || day > f) {
                Log.e("dd", "dd");
                ((FloatingActionButton) getActivity().findViewById(R.id.fab)).setClickable(false);
                Toast.makeText(getContext(), "돌아오는 날짜를 다시 확인해주세요.", Toast.LENGTH_LONG).show();
            } else {
                ((FloatingActionButton) getActivity().findViewById(R.id.fab)).setClickable(true);
            }
            ((TextView) getActivity().findViewById(R.id.date_view2)).setText(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
    }
}