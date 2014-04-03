package com.example.commoncents.model;

/**
 * Helper for InfoFragment.
 * @author Daniel
 *
 */
public class InfoHelper {

    /**
     * Builds a string of the info.
     * @param accountName accountName
     * @param balance balance
     * @param interest interest
     * @return String info
     */
    public static String infoBuilder(final String accountName,
            final double balance, final double interest) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Name: "
                     + accountName
                     + "\n");
        buffer.append("Balance: $"
                     + balance + "\n");
        buffer.append("Interest: "
                     + interest + "\n");
        return buffer.toString();
    }

}
