package com.example.meal.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.meal.R;

import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

@SuppressLint({"NewApi", "LocalSuppress"})
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        LocalDate localDate = LocalDate.of(year, month+1, dayOfMonth);
        if(getTag().equals("DatePicker"))
            ((TextView) getActivity().findViewById(R.id.date_view)).setText(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        else
            ((TextView) getActivity().findViewById(R.id.date_view2)).setText(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

    }
}