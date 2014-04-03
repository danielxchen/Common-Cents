package com.example.commoncents.model;

import java.util.ArrayList;

import com.example.sqlite.model.Transaction;

/**
 * Helps with HistoryFragment.
 * @author Daniel
 *
 */
public class HistoryHelper {

    /**
     * withdrawTotal.
     */
    private static double withdrawTotal = 0;

    /**
     * depositTotal.
     */
    private static double depositTotal = 0;

    /**
     * Builds a string of the history.
     * @param transactions list of transactions
     * @return String history
     */
    public static String historyBuilder(
            final ArrayList<Transaction> transactions) {
        StringBuffer buffer = new StringBuffer();
        withdrawTotal = 0;
        depositTotal = 0;
        buffer.append("Number of transactions in period:"
                        + transactions.size() + "\n\n");
        for (Transaction transaction: transactions) {
            if (transaction.getType().equals("withdraw")) {
                withdrawTotal += transaction.getAmount();
            } else {
                depositTotal += transaction.getAmount();
            }
            buffer.append(transaction.toString());
        }
        buffer.append("Withdraw Total: "
                     + withdrawTotal
                     + "   Deposit Total: " + depositTotal);
        return buffer.toString();
    }

    /**
     * fullHistory.
     * @param transactions transactions
     * @return string
     */
    public static String fullHistory(
            final ArrayList<Transaction> transactions) {
        StringBuffer buffer = new StringBuffer();
        withdrawTotal = 0;
        depositTotal = 0;
        buffer.append("Number of transactions in period:"
                        + transactions.size() + "\n\n");
        for (Transaction transaction: transactions) {
            if (transaction.getType().equals("withdraw")) {
                withdrawTotal += transaction.getAmount();
            } else {
                depositTotal += transaction.getAmount();
            }
            buffer.append(transaction.toFullString());
        }
        buffer.append("Withdraw Total: "
                     + withdrawTotal
                     + "   Deposit Total: " + depositTotal);
        return buffer.toString();
    }

    /**
     * withdrawHistory.
     * @param transactions transactions
     * @return string
     */
    public static String withdrawHistory(
            final ArrayList<Transaction> transactions) {
        StringBuffer buffer = new StringBuffer();
        withdrawTotal = 0;
        buffer.append("Number of Withdrawals in period:"
                        + transactions.size() + "\n\n");
        for (Transaction transaction: transactions) {
            withdrawTotal += transaction.getAmount();
            buffer.append(transaction.toTypeString());
        }
        buffer.append("Withdraw Total: "
                     + withdrawTotal);
        return buffer.toString();
    }

    /**
     * depositHistory.
     * @param transactions transactions
     * @return string
     */
    public static String depositHistory(
            final ArrayList<Transaction> transactions) {
        StringBuffer buffer = new StringBuffer();
        depositTotal = 0;
        buffer.append("Number of Deposits in period:"
                        + transactions.size() + "\n\n");
        for (Transaction transaction: transactions) {
            depositTotal += transaction.getAmount();
            buffer.append(transaction.toTypeString());
        }
        buffer.append("Deposit Total: "
                     + depositTotal);
        return buffer.toString();
    }

    /**
     * returns withdrawTotal.
     * @return withdrawTotal
     */
    public final double getWithdrawTotal() {
        return withdrawTotal;
    }

    /**
     * returns depositTotal.
     * @return depositTotal
     */
    public final double getDepositTotal() {
        return depositTotal;
    }

}
