package com.example.commoncents.ui;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Date entry fragment for date dialog.
 *
 * @author Robert Borowicz
 */
@SuppressLint("ValidFragment")
public class DateEntryFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

    /**
     * Text view.
     */
    private TextView text;

    /**
     * Constructor for a fragment.
     *
     * @param tex Textview
     */
    public DateEntryFragment(final TextView tex) {
        this.text = tex;
    }

    @Override
    public final Dialog onCreateDialog(final Bundle savedInstanceState) {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),
                                    this, year, month, day);
    }

    /**
     * Gets the text view.
     *
     * @return the text view
     */
    public final TextView getTextView() {
        return this.text;
    }

    /**
     * What to do when date is set.
     *
     * @param view The View
     * @param year The year
     * @param month The month
     * @param day The day
     */
    public final void onDateSet(final DatePicker view,
            final int year, final int month, final int day) {
        String monthText = String.valueOf(month + 1);
        if (monthText.length() == 1) {
            monthText = 0 + monthText;
        }
        String dayText = String.valueOf(day);
        if (dayText.length() == 1) {
            dayText = 0 + dayText;
        }
        text.setText(
            String.valueOf(year) + "-" + monthText + "-" + dayText);
    }
}