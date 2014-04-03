package com.example.commoncents.ui;

import com.example.commoncents.R;
import com.example.sqlite.helper.DatabaseHelper;
import com.example.sqlite.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * LoginActivity.
 * @author Daniel
 *
 */
public class LoginActivity extends Activity {

    /**
     * currentUser.
     */
    protected static String currentUser;

    /**
     * db.
     */
    private DatabaseHelper db;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
    }

    /**
     * goToMain.
     * @param view view
     */
    public final void goToMain(final View view) {
        EditText usernameText =
                (EditText) findViewById(R.id.usernameText);
        EditText passwordText =
                (EditText) findViewById(R.id.passwordText);
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (this.checkLogin(username, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            currentUser = username;
            startActivity(intent);
        } else {
            usernameText.setText("");
            passwordText.setText("");
            Toast.makeText(LoginActivity.this,
                    "Wrong username or password",
                Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * checkLogin.
     * @param username username
     * @param password password
     * @return true or false
     */
    private boolean checkLogin(final String username,
            final String password) {
        if (db.containsUser(username)) {
            User user = db.getUser(username);
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * goToRegister.
     * @param view view
     */
    public final void goToRegister(final View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the
        // action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }
}
