package com.example.commoncents.ui;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.commoncents.R;
import com.example.sqlite.helper.DatabaseHelper;
import com.example.sqlite.model.User;

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
import android.widget.Toast;
import android.support.v4.app.NavUtils;

/**
 * RegisterActivity.
 * @author Daniel
 *
 */
public class RegisterActivity extends Activity {

    /**
     * db.
     */
    private DatabaseHelper db;

    /**
     * username.
     */
    private String username;

    /**
     * password.
     */
    private String password;

    /**
     * name.
     */
    private String name;

    /**
     * email.
     */
    private String email;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Show the Up button in the action bar.
        setupActionBar();
        db = new DatabaseHelper(this);
    }

    /**
     * Register a user.
     * @param view view
     */
    public final void registerUser(final View view) {
        EditText usernameText = (EditText) findViewById(
            R.id.registerUsername);
        EditText passwordText = (EditText) findViewById(
            R.id.registerPassword);
        EditText nameText = (EditText) findViewById(
            R.id.registerName);
        EditText emailText = (EditText) findViewById(
            R.id.registerEmail);
        username = usernameText.getText().toString();
        password = passwordText.getText().toString();
        name = nameText.getText().toString();
        email = emailText.getText().toString();

        if (checkRegistration(username, password, name, email)) {
            db.createUser(new User(
                    username, password, name, email));
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, "Invalid information",
                Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Check registration.
     * @param username username
     * @param password password
     * @param name name
     * @param email email
     * @return true or false
     */
    private boolean checkRegistration(final String username,
            final String password,
            final String name,
            final String email) {
        Pattern p3 = Pattern.compile(
                "[A-Za-z]{1}[\\w\\.\\-]*@[A-Za-z]{1}"
                + "[\\w\\.\\-]*(.com|.org|.net)");
        Matcher m3 = p3.matcher(email);
        return (password.length() >= 8)
                && m3.find()
                && !db.containsUser(username);
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.register, menu);
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
    	Intent refresh = new Intent(this, RegisterActivity.class); 
    	startActivity(refresh); 
    } 
}

