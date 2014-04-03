package com.example.commoncents.ui;

import com.example.commoncents.R;
import com.example.commoncents.model.TransactionHelper;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * TransactionFragment.
 * @author Daniel
 *
 */
public class TransactionFragment extends Fragment {

    /**
     * rootView.
     */
    private View rootView;

    /**
     * currentAccount.
     */
    private Account currentAccount;

    /**
     * db.
     */
    private DatabaseHelper db;

    /**
     * flag.
     */
    private boolean flag;

    /**
     * amountText.
     */
    private EditText amountText;

    /**
     * withdrawButton.
     */
    private RadioButton withdrawButton;

    /**
     * depositButton.
     */
    private RadioButton depositButton;

    /**
     * dateButton.
     */
    private Button dateButton;

    /**
     * dateText.
     */
    private TextView dateText;

    @Override
    public final View onCreateView(final LayoutInflater inflater,
        final ViewGroup container, final Bundle savedInstanceState) {
        currentAccount = InfoFragment.getCurrentAccount();
        db = new DatabaseHelper(getActivity());

        rootView = inflater.inflate(
            R.layout.fragment_transaction, container, false);
        dateButton = (Button) rootView.findViewById(R.id.dateButton);
        dateText = (TextView) rootView.findViewById(R.id.dateText);
        amountText = (EditText) rootView.findViewById(R.id.transaction);

        withdrawButton = (RadioButton) rootView.findViewById(
            R.id.withdraw);
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onRadioButtonClicked(v);
            }
        });

        depositButton = (RadioButton) rootView.findViewById(
            R.id.deposit);
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onRadioButtonClicked(v);
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                showDateEntry(v);
            }
        });

        Button acceptButton = (Button) rootView.findViewById(
            R.id.acceptButton);
        acceptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                accept(v);
            }
        });

        Button declineButton = (Button) rootView.findViewById(
            R.id.declineButton);
        declineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                decline(v);
            }
        });

        return rootView;
    }

    /**
     * Radio button clicked.
     * @param view view
     * @return true or false
     */
    public final boolean onRadioButtonClicked(final View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.withdraw:
                if (checked) {
                    flag = true;
                }
                break;
            case R.id.deposit:
                if (checked) {
                    flag = false;
                }
                break;
            default:
                break;
        }
        return flag;
    }

    /**
     * Shows a Date.
     * @param view view
     */
    public final void showDateEntry(final View view) {
        DialogFragment newFragment = new DateEntryFragment(dateText);
        newFragment.show(getFragmentManager(), "dateEntry");
    }

    /**
     * Accepts.
     * @param view view
     */
    public final void accept(final View view) {
        String amount = amountText.getText().toString();

        if (!amount.equals("")) {
            TransactionHelper.updateAccount(
                    currentAccount, amount, flag);
            db.updateAccount(currentAccount);
            db.createTransaction(
                    TransactionHelper.createTransaction(
                            currentAccount, flag,
                            amount, dateText.getText().toString()));
            decline(view);
        }
    }

    /**
     * Declines.
     * @param view view
     */
    public final void decline(final View view) {
        amountText.setText("");
        withdrawButton.setChecked(false);
        depositButton.setChecked(false);
        dateText.setText("");
        getActivity().getActionBar().setSelectedNavigationItem(0);
    }
}