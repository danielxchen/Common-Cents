package com.example.commoncents.ui;

import com.example.commoncents.R;
import com.example.commoncents.model.InfoHelper;
import com.example.sqlite.model.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Information about account fragment tab.
 *
 * @author Robert Borowicz
 */
public class InfoFragment extends Fragment {

    /**
     * Current account.
     */
    private static Account currentAccount;

    /**
     * Index.
     */
    private int index;

    @Override
    public final View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        index = (Integer) intent.getSerializableExtra("index");
        currentAccount = MainActivity.accountsList.get(index);
        View rootView =
                inflater.inflate(R.layout.fragment_info,
                                 container, false);
        TextView accountInfo =
                (TextView) rootView.findViewById(
                          R.id.accountInfo);

        currentAccount = MainActivity.accountsList.get(index);
        accountInfo.setText(InfoHelper.infoBuilder(
                currentAccount.getAccountName(),
                currentAccount.getBalance(),
                currentAccount.getInterest()));

        return rootView;
    }

    /**
     * Gets the current account.
     * @return Current account
     */
    public static Account getCurrentAccount() {
        return currentAccount;
    }
}
