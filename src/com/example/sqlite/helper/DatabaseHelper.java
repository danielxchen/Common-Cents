package com.example.sqlite.helper;

import java.util.ArrayList;

import com.example.sqlite.model.Account;
import com.example.sqlite.model.Transaction;
import com.example.sqlite.model.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database Helper for app.
 * @author Daniel
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 5;

    /**
     * Database name.
     */
    private static final String DATABASE_NAME = "dataManager";

    /**
     * Tables.
     */
    private static final String TABLE_USER = "users";

    /**
     * Tables.
     */
    private static final String TABLE_ACCOUNT = "accounts";

    /**
     * Tables.
     */
    private static final String TABLE_TRANSACTION = "transactions";

    /**
     * Id column.
     */
    private static final String KEY_ID = "id";

    /**
     * User table columns.
     */
    private static final String KEY_USERNAME = "username";

    /**
     * User table columns.
     */
    private static final String KEY_PASSWORD = "password";

    /**
     * User table columns.
     */
    private static final String KEY_NAME = "name";

    /**
     * User table columns.
     */
    private static final String KEY_EMAIL = "email";

    /**
     * Account table columns.
     */
    private static final String KEY_ACCOUNTOWNER = "accountowner";

    /**
     * Account table columns.
     */
    private static final String KEY_ACCOUNTNAME = "accountname";

    /**
     * Account table columns.
     */
    private static final String KEY_BALANCE = "balance";

    /**
     * Account table columns.
     */
    private static final String KEY_INTEREST = "interest";

    /**
     * Transaction table columns.
     */
    private static final String KEY_TYPE = "type";

    /**
     * Transaction table columns.
     */
    private static final String KEY_AMOUNT = "amount";

    /**
     * Transaction table columns.
     */
    private static final String KEY_DATE = "date";

    /**
     * User table create statement.
     */
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERNAME + " TEXT," + KEY_PASSWORD
            + " TEXT," + KEY_NAME + " TEXT," + KEY_EMAIL
            + " TEXT" + ")";

    /**
     * Account table create statement.
     */
    private static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE "
            + TABLE_ACCOUNT + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ACCOUNTOWNER
            + " TEXT," + KEY_ACCOUNTNAME + " TEXT,"
            + KEY_BALANCE + " REAL," + KEY_INTEREST + " REAL" + ")";

    /**
     * Transaction table create statement.
     */
    private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE "
            + TABLE_TRANSACTION + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERNAME + " TEXT," + KEY_ACCOUNTNAME + " TEXT,"
            + KEY_TYPE + " TEXT," + KEY_AMOUNT + " REAL," + KEY_DATE
            + " TEXT" + ")";

    /**
     * Constructor.
     * @param context context
     */
    public DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates tables.
     * @param db database
     */
    @Override
    public final void onCreate(final SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_TRANSACTION);
    }

    /**
     * Updates tables.
     * @param db database
     * @param oldVersion old table
     * @param newVersion new table
     */
    @Override
    public final void onUpgrade(final SQLiteDatabase db,
            final int oldVersion, final int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);

        // create new tables
        onCreate(db);
    }

    /**
     * Creates tables.
     * @param user user
     */
    public final void createUser(final User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * Gets user.
     * @param username username
     * @return user user
     */
    public final User getUser(final String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE "
                + KEY_USERNAME + " = '" + username + "'";

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst() && c != null) {
            User user = new User();
            user.setUsername(c.getString(c.getColumnIndex(KEY_USERNAME)));
            user.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
            user.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            user.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
            return user;
        } else {
            return null;
        }
    }

    /**
     * Contains User.
     * @param username username
     * @return true or false
     */
    public final boolean containsUser(final String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE "
                + KEY_USERNAME + " = '" + username + "'";
        Cursor c = db.rawQuery(selectQuery, null);
        return c.moveToFirst() && c != null;
    }

    /**
     * Creates Account.
     * @param account account
     */
    public final void createAccount(final Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNTNAME, account.getAccountName());
        values.put(KEY_ACCOUNTOWNER, account.getAccountOwner());
        values.put(KEY_BALANCE, account.getBalance());
        values.put(KEY_INTEREST, account.getInterest());

        db.insert(TABLE_ACCOUNT, null, values);
        db.close();
    }

    /**
     * Updates account.
     * @param account account
     */
    public final void updateAccount(final Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACCOUNTNAME, account.getAccountName());
        values.put(KEY_ACCOUNTOWNER, account.getAccountOwner());
        values.put(KEY_BALANCE, account.getBalance());
        values.put(KEY_INTEREST, account.getInterest());

        db.update(TABLE_ACCOUNT, values, KEY_ACCOUNTNAME + " = '"
                + account.getAccountName() + "' and "
                + KEY_ACCOUNTOWNER + " = '"
                + account.getAccountOwner() + "'", null);
        db.close();
    }

    /**
     * Gets Accounts.
     * @param username username
     * @return list of accounts
     */
    public final ArrayList<Account> getAccounts(final String username) {
        ArrayList<Account> accounts = new ArrayList<Account>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ACCOUNT + " WHERE "
                + KEY_ACCOUNTOWNER + " = '" + username + "'";
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                Account account = new Account();
                account.setAccountName(c.getString(c.getColumnIndex(KEY_ACCOUNTNAME)));
                account.setAccountOwner(c.getString(c.getColumnIndex(KEY_ACCOUNTOWNER)));
                account.setBalance(c.getDouble(c.getColumnIndex(KEY_BALANCE)));
                account.setInterest(c.getDouble(c.getColumnIndex(KEY_INTEREST)));
                accounts.add(account);
            } while (c != null && c.moveToNext());
        }

        return accounts;
    }

    /**
     * Create Transaction.
     * @param transaction transaction
     */
    public final void createTransaction(final Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();

        System.out.println("a");

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, transaction.getUserName());
        values.put(KEY_ACCOUNTNAME, transaction.getAccountName());
        values.put(KEY_TYPE, transaction.getType());
        values.put(KEY_AMOUNT, transaction.getAmount());
        values.put(KEY_DATE, transaction.getDate());

        db.insert(TABLE_TRANSACTION, null, values);
        db.close();
    }

    /**
     * Gets transactions.
     * @param userName userName
     * @param accountName accountName
     * @param beginDate beginDate
     * @param endDate endDate
     * @return transactions
     */
    public final ArrayList<Transaction> getTransactions(final String userName,
            final String accountName, final String beginDate,
            final String endDate) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION
                + " WHERE date(" + KEY_DATE + ") between date('"
                + beginDate + "') AND date('" + endDate + "')";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                if (c.getString(c.getColumnIndex(KEY_USERNAME)).equals(userName) && 
                        c.getString(c.getColumnIndex(KEY_ACCOUNTNAME)).equals(accountName)) {
                    Transaction transaction = new Transaction();
                    transaction.setUserName(c.getString(c.getColumnIndex(KEY_USERNAME)));
                    transaction.setAccountName(c.getString(c.getColumnIndex(KEY_ACCOUNTNAME)));
                    transaction.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
                    transaction.setAmount(c.getDouble(c.getColumnIndex(KEY_AMOUNT)));
                    transaction.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                    transactions.add(transaction);
                }
            } while (c != null && c.moveToNext());
        }

        return transactions;
    }

    /**
     * getWithdrawals.
     * @param userName user
     * @param beginDate begin
     * @param endDate end
     * @return transactions
     */
    public final ArrayList<Transaction> getWithdrawals(final String userName,
            final String beginDate,
            final String endDate) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION
                + " WHERE date(" + KEY_DATE + ") between date('"
                + beginDate + "') AND date('" + endDate + "')";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
                if (c.getString(c.getColumnIndex(KEY_TYPE)).equals("withdraw")
            			&& c.getString(c.getColumnIndex(KEY_USERNAME)).equals(userName)) {
	            	Transaction transaction = new Transaction();
	                transaction.setUserName(c.getString(c.getColumnIndex(KEY_USERNAME)));
	                transaction.setAccountName(c.getString(c.getColumnIndex(KEY_ACCOUNTNAME)));
	                transaction.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
	                transaction.setAmount(c.getDouble(c.getColumnIndex(KEY_AMOUNT)));
	                transaction.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
	                transactions.add(transaction);  
            	}
            } while (c != null && c.moveToNext());
        }

        return transactions;
    }

    /**
     * getDesposits.
     * @param userName user
     * @param beginDate begin
     * @param endDate end
     * @return transactions
     */
    public final ArrayList<Transaction> getDeposits(final String userName,
    		final String beginDate,
            final String endDate) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION
                + " WHERE date(" + KEY_DATE + ") between date('"
                + beginDate + "') AND date('" + endDate + "')";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
            	if (c.getString(c.getColumnIndex(KEY_TYPE)).equals("deposit")
            			&& c.getString(c.getColumnIndex(KEY_USERNAME)).equals(userName)) {
	            	Transaction transaction = new Transaction();
	                transaction.setUserName(c.getString(c.getColumnIndex(KEY_USERNAME)));
	                transaction.setAccountName(c.getString(c.getColumnIndex(KEY_ACCOUNTNAME)));
	                transaction.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
	                transaction.setAmount(c.getDouble(c.getColumnIndex(KEY_AMOUNT)));
	                transaction.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
	                transactions.add(transaction);  
            	}
            } while (c != null && c.moveToNext());
        }

        return transactions;
    }

    /**
     * getAllTransactions.
     * @param userName user
     * @param beginDate begin
     * @param endDate end
     * @return transactions
     */
    public final ArrayList<Transaction> getAllTransactions(
    		final String userName,
    		final String beginDate,
            final String endDate) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTION
                + " WHERE date(" + KEY_DATE + ") between date('"
                + beginDate + "') AND date('" + endDate + "')";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            do {
            	if (c.getString(c.getColumnIndex(KEY_USERNAME)).equals(userName)) {
	            	Transaction transaction = new Transaction();
	                transaction.setUserName(c.getString(c.getColumnIndex(KEY_USERNAME)));
	                transaction.setAccountName(c.getString(c.getColumnIndex(KEY_ACCOUNTNAME)));
	                transaction.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
	                transaction.setAmount(c.getDouble(c.getColumnIndex(KEY_AMOUNT)));
	                transaction.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
		            transactions.add(transaction);   	
            	}
            } while (c != null && c.moveToNext());
        }

        return transactions;
    }
}
