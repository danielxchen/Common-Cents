package com.example.commoncents.model;

import com.example.sqlite.model.Account;
import com.example.sqlite.model.Transaction;

import static org.junit.Assert.*;

/**
 * Tests TransactionHelper.
 * @author Daniel
 *
 */
public class TransactionTest {
	
	/**
	 * transaction1.
	 */
	private Transaction transaction1 = new Transaction("User1", "Account1", "withdrawal", 5.00, "2014-03-31");
	private Account account1 = new Account("Account1", "Bob", 100, 0.05);
	
	public void testCreateTransaction() {
		Transaction transaction = TransactionHelper.createTransaction(account1, true, "5.00", "2014-03-31");
		assertEquals(transaction1, transaction);
		
	}

}
