package com.example.commoncents.ui;

import java.util.ArrayList;

import com.example.commoncents.R;
import com.example.commoncents.model.HistoryHelper;
import com.example.sqlite.helper.DatabaseHelper;
import com.example.sqlite.model.Account;
import com.example.sqlite.model.Transaction;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * History Fragment tab.
 *
 * @author Robert Borowicz
 */
public class HistoryFragment extends Fragment {

    /**
     * Current account.
     */
    private Account currentAccount;

    /**
     * Current database.
     */
    private DatabaseHelper db;

    /**
     * Transactions.
     */
    private ArrayList<Transaction> transactions;

    /**
     * Date button.
     */
    private Button fromDateButton;

    /**
     * Text view for date.
     */
    private TextView fromDateText;

    /**
     * To date button.
     */
    private Button toDateButton;

    /**
     * To date text view.
     */
    private TextView toDateText;

    /**
     * Submit button.
     */
    private Button submitButton;

    /**
     * Text view for history.
     */
    private TextView historyText;

    @Override
    public final View onCreateView(final LayoutInflater inflater,
                                   final ViewGroup container,
                                   final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history,
                                         container, false);
        currentAccount = InfoFragment.getCurrentAccount();
        db = new DatabaseHelper(getActivity());
        fromDateButton = (Button) rootView.findViewById(R.id.fromDateButton);
        fromDateText = (TextView) rootView.findViewById(R.id.fromDateText);
        toDateButton = (Button) rootView.findViewById(R.id.toDateButton);
        toDateText = (TextView) rootView.findViewById(R.id.toDateText);
        submitButton = (Button) rootView.findViewById(R.id.submitButton);
        historyText = (TextView) rootView.findViewById(R.id.transaction_history);

        fromDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                fromDateEntry(v);
            }
        });

        toDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                toDateEntry(v);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                showHistory(v);
            }
        });

        return rootView;
    }

    /**
     * Creates a date dialog.
     *
     * @param view the View
     */
    public final void fromDateEntry(final View view) {
        DialogFragment newFragment = new DateEntryFragment(fromDateText);
        newFragment.show(getFragmentManager(), "fromDateEntry");
    }

    /**
     * Makes a dateEntry.
     * @param view the View
     */
    public final void toDateEntry(final View view) {
        DialogFragment newFragment = new DateEntryFragment(toDateText);
        newFragment.show(getFragmentManager(), "toDateEntry");
    }

    /**
     * Shows the current history.
     * @param view the View
     */
    public final void showHistory(final View view) {
        if (!fromDateText.getText().toString().equals("")
            && !toDateText.getText().toString().equals("")) {
            transactions = db.getTransactions(currentAccount.getAccountOwner(),
                                    currentAccount.getAccountName(),
                                    fromDateText.getText().toString(),
                                    toDateText.getText().toString());

            historyText.setText(HistoryHelper.historyBuilder(transactions));
            fromDateText.setText("");
            toDateText.setText("");
        }
    }
}
