package com.example.commoncents.ui;

import java.util.ArrayList;

import com.example.commoncents.R;
import com.example.sqlite.helper.DatabaseHelper;
import com.example.sqlite.model.Account;
import com.example.sqlite.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

/**
 * MainActivity.
 * @author Daniel
 *
 */
public class MainActivity extends Activity {

    /**
     * currentUser.
     */
    private String currentUser;

    /**
     * db.
     */
    private DatabaseHelper db;

    /**
     * accountsList.
     */
    protected static ArrayList<Account> accountsList;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Show the Up button in the action bar.
        setupActionBar();

        currentUser = LoginActivity.currentUser;
        db = new DatabaseHelper(this);

        TextView nameText = (TextView) findViewById(R.id.yourName);
        User user = db.getUser(currentUser);
        nameText.setText(user.getName() + "'s Accounts");

        accountsList = db.getAccounts(currentUser);
        if (!accountsList.isEmpty()) {
            for (int i = 0; i < accountsList.size(); i++) {
                Button accountButton = new Button(this);
                accountButton.setText(accountsList.get(i)
                        .getAccountName());
                LinearLayout ll = (LinearLayout) findViewById(
                    R.id.mainActivityLayout);
                LayoutParams lp = new LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT);
                ll.addView(accountButton, lp);
                final int index = i;
                accountButton.setOnClickListener(
                        new View.OnClickListener() {

                    @Override
                    public void onClick(final View v) {
                        Intent intent = new Intent(
                            MainActivity.this,
                            AccountActivity.class);
                        intent.putExtra("index", index);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    /**
     * addAccount.
     * @param view view
     */
    public final void addAccount(final View view) {
        Intent intent = new Intent(this, AddAccountActivity.class);
        startActivity(intent);
    }

    /**
     * viewHistory.
     * @param view view
     */
    public final void viewHistory(final View view) {
        Intent intent = new Intent(this, TransactionHistoryActivity.class);
        startActivity(intent);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to
        // the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
