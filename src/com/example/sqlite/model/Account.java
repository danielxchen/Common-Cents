package com.example.sqlite.model;

/**
 * Account class.
 * @author Daniel
 *
 */
public class Account {

    /**
     * accountName.
     */
    private String accountName;

    /**
     * accountOwner.
     */
    private String accountOwner;

    /**
     * balance.
     */
    private double balance;

    /**
     * interest.
     */
    private double interest;

    /**
     * Constructor.
     */
    public Account() {

    }

    /**
     * Constructor.
     * @param accountName accountName
     * @param accountOwner accountOwner
     * @param balance balance
     * @param interest interest
     */
    public Account(final String accountName, final String accountOwner,
            final double balance, final double interest) {
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.balance = balance;
        this.interest = interest;
    }

    /**
     * Gets account name.
     * @return accountName accountName
     */
    public final String getAccountName() {
        return this.accountName;
    }

    /**
     * Sets account name.
     * @param accountName accountName
     */
    public final void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets account owner.
     * @return accountOnwer accountOwner
     */
    public final String getAccountOwner() {
        return this.accountOwner;
    }

    /**
     * Sets account owner.
     * @param accountOwner accountOwner
     */
    public final void setAccountOwner(final String accountOwner) {
        this.accountOwner = accountOwner;
    }

    /**
     * Gets balance.
     * @return balance balance
     */
    public final double getBalance() {
        return this.balance;
    }

    /**
     * Sets balance.
     * @param balance balance
     */
    public final void setBalance(final double balance) {
        this.balance = balance;
    }

    /**
     * Gets interest.
     * @return interest interest
     */
    public final double getInterest() {
        return this.interest;
    }

    /**
     * Sets interest.
     * @param interest interest
     */
    public final void setInterest(final double interest) {
        this.interest = interest;
    }

}
