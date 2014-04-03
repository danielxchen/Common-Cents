package com.example.commoncents.model;

import com.example.sqlite.model.Account;
import com.example.sqlite.model.Transaction;

/**
 * Helps with TransactionFragment.
 * @author Daniel
 *
 */
public class TransactionHelper {

    /**
     * Updates an account.
     * @param currentAccount currentAccount
     * @param amount amount
     * @param flag flag
     * @return currentAccount
     */
    public static Account updateAccount(final Account currentAccount,
            final String amount, final boolean flag) {
        double number = Double.parseDouble(amount);
        if (flag) {
            number *= -1;
        }
        currentAccount.setBalance(currentAccount.getBalance() + number);

        return currentAccount;
    }

    /**
     * Creates a transaction.
     * @param currentAccount currentAccount
     * @param flag flag
      * @param amount amount
     * @param dateText dateText
     * @return transaction
     */
    public static Transaction createTransaction(
            final Account currentAccount, final boolean flag,
            final String amount, final String dateText) {
        Transaction transaction = new Transaction();
        transaction.setUserName(
            currentAccount.getAccountOwner());
        transaction.setAccountName(
            currentAccount.getAccountName());
        if (flag) {
            transaction.setType("withdraw");
        } else {
            transaction.setType("deposit");
        }
        transaction.setAmount(Double.parseDouble(amount));
        transaction.setDate(dateText);

        return transaction;

    }

}
