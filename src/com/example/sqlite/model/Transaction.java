package com.example.sqlite.model;

/**
 * Transaction class.
 * @author Daniel
 *
 */
public class Transaction {

    /**
     * userName.
     */
    private String userName;

    /**
     * accountName.
     */
    private String accountName;

    /**
     * type.
     */
    private String type;

    /**
     * amount.
     */
    private double amount;

    /**
     * date.
     */
    private String date;

    /**
     * Constructor.
     */
    public Transaction() {

    }

    /**
     * Constructor.
     * @param userName userName
     * @param accountName accountName
     * @param type type
     * @param amount amount
     * @param date date
     */
    public Transaction(final String userName,
            final String accountName,
            final String type,
            final double amount,
            final String date) {
        this.userName = userName;
        this.accountName = accountName;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    /**
     * Gets username.
     * @return userName userName
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * Sets username.
     * @param userName userName
     */
    public final void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Gets accountName.
     * @return accountName accountName
     */
    public final String getAccountName() {
        return accountName;
    }

    /**
     * Sets accountName.
     * @param accountName accountName
     */
    public final void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets type.
     * @return type type
     */
    public final String getType() {
        return type;
    }

    /**
     * Sets type.
     * @param type type
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets amount.
     * @return amount amount
     */
    public final double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     * @param amount amount
     */
    public final void setAmount(final double amount) {
        this.amount = amount;
    }

    /**
     * Gets date.
     * @return date date
     */
    public final String getDate() {
        return date;
    }

    /**
     * Sets date.
     * @param date date
     */
    public final void setDate(final String date) {
        this.date = date;
    }

    /**
     * toString.
     * @return string string
     */
    public final String toString() {
        return type + " | " + amount + " | " + date + "\n"
                + "_______________________________" + "\n";
    }

    /**
     * toTypeString.
     * @return string string
     */
    public final String toTypeString() {
        return accountName + " | " + amount + " | " + date + "\n"
                + "_______________________________" + "\n";
    }

    /**
     * toFullString.
     * @return string string
     */
    public final String toFullString() {
        return accountName + " | " + type + " | " + amount + " | " + date + "\n"
                + "_______________________________" + "\n";
    }

}

