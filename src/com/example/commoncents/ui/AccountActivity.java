package com.example.commoncents.ui;

import com.example.commoncents.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


/**
 * Account fragment activity for the account view.
 *
 * @author Daniel
 */
public class AccountActivity extends FragmentActivity
                             implements ActionBar.TabListener {

    /**
     * View pager.
     */
    private ViewPager vp;

    /**
     * Custom tab adapter.
     */
    private AccountTabsPagerAdapter adapter;

    /**
     * Current action bar.
     */
    private ActionBar actionBar;

    /**
     * Names of tabs.
     */
    private String[] tabNames =
        {"Account Info", "Transaction History", "Make Transaction"};

    /**
     * Method that creates the view.
     *
     * @param savedInstanceState current saved instance state
     */
    @SuppressLint("NewApi")
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        vp = (ViewPager) findViewById(R.id.pager);
        adapter = new AccountTabsPagerAdapter(getSupportFragmentManager());
        actionBar = getActionBar();

        vp.setAdapter(adapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String name : tabNames) {
            actionBar.addTab(
                    actionBar.newTab().setText(name).setTabListener(this));
        }

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(
                    final int arg0, final float arg1, final int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(final int arg0) {
            }
        });
    }

    @Override
    public void onTabReselected(
            final Tab tab, final FragmentTransaction ft) {
    }

    @Override
    public final void onTabSelected(final Tab tab,
                        final FragmentTransaction ft) {
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(final Tab tab, final FragmentTransaction ft) {
        // TODO Auto-generated method stub
    }

}
