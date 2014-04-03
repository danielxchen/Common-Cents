package com.example.sqlite.model;

import java.util.ArrayList;

/**
 * User class.
 * @author Daniel
 *
 */
public class User {

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

    /**
     * accounts.
     */
    private ArrayList<Account> accounts;

    /**
     * Constructor.
     */
    public User() {

    }

    /**
     * Constructor.
     * @param username username
     * @param password password
     * @param name name
     * @param email email
     */

    public User(final String username,
         final String password,
         final String name,
         final String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets username.
     * @return username
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * Sets username.
     * @param username username
     */
    public final void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets password.
     * @return password
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * Sets a password.
     * @param password password
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets a name.
     * @return name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * Sets a name.
     * @param name name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets an email.
     * @return email
     */
    public final String getEmail() {
        return this.email;
    }

    /**
     * Sets an email.
     * @param email email
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets an account.
     * @param i int i
     * @return account
     */
    public final Account getAccount(final int i) {
        return this.accounts.get(i);
    }

    /**
     * Get all accounts.
     * @return accounts
     */
    public final ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    /**
     * Add an account.
     * @param account account
     */
    public final void addAccount(final Account account) {
        this.accounts.add(account);
    }

    /**
     * Removes an account.
     * @param i index
     */
    public final void removeAccount(final int i) {
        this.accounts.remove(i);
    }

}
