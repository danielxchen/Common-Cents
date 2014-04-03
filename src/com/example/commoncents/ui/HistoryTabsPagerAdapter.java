package com.example.commoncents.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * TabsPageAdapter.
 * @author Daniel
 *
 */
public class HistoryTabsPagerAdapter extends FragmentPagerAdapter {

    /**
     * Constructor.
     * @param fm FragmentManager
     */
    public HistoryTabsPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    /**
     * Gets an item.
     * @param index index
     * @return fragment
     */
    public final Fragment getItem(final int index) {
        switch(index) {
            case 0:
                return new WithdrawHistoryFragment();
            case 1:
                return new DepositHistoryFragment();
            case 2:
                return new TransactionHistoryFragment();
            default:
                break;
        }

        return null;
    }

    /**
     * Gets count.
     * @return 3
     */
    public final int getCount() {
        return 3;
    }
}