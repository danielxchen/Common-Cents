package com.example.commoncents.ui;

import java.util.Locale;

import com.example.commoncents.R;
import com.example.sqlite.helper.DatabaseHelper;
import com.example.sqlite.model.Account;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

/**
 * Activity for adding an account.
 *
 * @author Byte-Me
 *
 */
public class AddAccountActivity extends Activity {

    /**
     * The name of the current user.
     */
    private String currentUser;

    /**
     * The current database.
     */
    private DatabaseHelper db;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        // Show the Up button in the action bar.
        setupActionBar();
        currentUser = LoginActivity.currentUser;
        db = new DatabaseHelper(this);
    }

    /**
     * What to do if the accept button is pushed.
     *
     * @param view the View
     */
    public final void accept(final View view) {
        EditText accountNameText =
                (EditText) findViewById(R.id.accountName);
        EditText startingBalanceText =
                (EditText) findViewById(R.id.balance);
        EditText interestText = (EditText) findViewById(R.id.interest);
        String accountName = accountNameText.getText().toString();
        String startingBalance =
                startingBalanceText.getText().toString();
        String interest = interestText.getText().toString();
        if (!accountName.equals("")
            && !startingBalance.equals("") && !interest.equals("")) {
            double startingBalanceNum =
                    Double.parseDouble(startingBalance);
            double interestNum = Double.parseDouble(interest);
            Account account = new Account(accountName,
                                          currentUser,
                                          startingBalanceNum,
                                          interestNum);
            db.createAccount(account);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            this.decline(view);
        }
    }

    /**
     * What to do on decline button.
     *
     * @param view the View
     */
    public final void decline(final View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_account, menu);
        return true;
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
            return true;
        default:
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void changeLocale(MenuItem item) {
    	Locale current = getResources().getConfiguration().locale;
    	if (current.equals(new Locale("es")))
    	{
    		setLocale("en");
    	}
    	else
    	{
    		setLocale("es");
    	}
    }
    
    private void setLocale(String lang) { 
    	Locale myLocale = new Locale(lang); 
    	Resources res = getResources(); 
    	DisplayMetrics dm = res.getDisplayMetrics(); 
    	Configuration conf = res.getConfiguration(); 
    	conf.locale = myLocale; 
    	res.updateConfiguration(conf, dm); 
    	Intent refresh = new Intent(this, AddAccountActivity.class); 
    	startActivity(refresh); 
    } 

}
