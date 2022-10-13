package com.example.bankingsystem_sparks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "bank";
    public static final String DB_TABLE_1_NAME = "users";
    public static final String DB_TABLE_2_NAME = "transactions";
    public static final String DB_ID_COLUMN_NAME = "id";
    public static final String DB_userNAME_COLUMN_NAME = "name";
    public static final String DB_JOB_COLUMN_NAME = "job";
    public static final String DB_CURRENT_BALANCE_COLUMN_NAME = "balance";
    public static final String DB_EMAIL_COLUMN_NAME = "email";
    public static final String DB_FROM_COLUMN_NAME = "fromUser";
    public static final String DB_TO_COLUMN_NAME = "toUser";
    public static final String DB_AMOUNT_COLUMN_NAME = "amount";
    public static final int DB_VERSION = 21;

    MyDatabase(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String table_1_query = "CREATE TABLE " + DB_TABLE_1_NAME + " ("
                + DB_ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DB_userNAME_COLUMN_NAME + " TEXT,"
                + DB_EMAIL_COLUMN_NAME + " TEXT UNIQUE,"
                + DB_JOB_COLUMN_NAME + " TEXT,"
                + DB_CURRENT_BALANCE_COLUMN_NAME + " REAL)";
        String table_2_query = "CREATE TABLE " + DB_TABLE_2_NAME + " ("
                + DB_FROM_COLUMN_NAME + " TEXT,"
                + DB_TO_COLUMN_NAME + " TEXT,"
                + DB_AMOUNT_COLUMN_NAME + " REAL)";
        sqLiteDatabase.execSQL(table_1_query);
        sqLiteDatabase.execSQL(table_2_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_1_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_2_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertUser(Users user) {
        //hna l key hya asm l column
        //getWritableDatabase() DE A7SAN FL WRITE
        //getReadableDatabase() DE A7SAN FL READ
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.DB_JOB_COLUMN_NAME, user.getUser_job());
        values.put(MyDatabase.DB_EMAIL_COLUMN_NAME, user.getUser_email());
        values.put(MyDatabase.DB_CURRENT_BALANCE_COLUMN_NAME, user.getUser_balance());
        values.put(MyDatabase.DB_userNAME_COLUMN_NAME, user.getUser_name());
        long result = database.insert(MyDatabase.DB_TABLE_1_NAME, null, values);
        return result != -1;
    }

    public boolean updateUserInfo(Users user) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.DB_JOB_COLUMN_NAME, user.getUser_job());
        values.put(MyDatabase.DB_EMAIL_COLUMN_NAME, user.getUser_email());
        values.put(MyDatabase.DB_CURRENT_BALANCE_COLUMN_NAME, user.getUser_balance());
        values.put(MyDatabase.DB_userNAME_COLUMN_NAME, user.getUser_name());
        String[] args = {String.valueOf(user.getUser_id())};
        long result = database.update(MyDatabase.DB_TABLE_1_NAME, values, "id=?", args);
        return result > 0;
    }

    public long getUsersCount() {
        SQLiteDatabase database = getWritableDatabase();
        return DatabaseUtils.queryNumEntries(database, MyDatabase.DB_TABLE_1_NAME);
    }

    public ArrayList<Users> getAllUsers() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<Users> users = new ArrayList<>();
        /*Cursor cursor = database.rawQuery("SELECT DISTINCT " +DB_ID_COLUMN_NAME+","+DB_JOB_COLUMN_NAME+","+DB_userNAME_COLUMN_NAME+","+DB_EMAIL_COLUMN_NAME+","+DB_CURRENT_BALANCE_COLUMN_NAME+
                " FROM " + MyDatabase.DB_TABLE_1_NAME, null);
         */
        Cursor cursor = database.rawQuery("SELECT * FROM " + MyDatabase.DB_TABLE_1_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabase.DB_ID_COLUMN_NAME));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_userNAME_COLUMN_NAME));
                String job = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_JOB_COLUMN_NAME));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_EMAIL_COLUMN_NAME));
                long balance = cursor.getLong(cursor.getColumnIndexOrThrow(MyDatabase.DB_CURRENT_BALANCE_COLUMN_NAME));
                Users u = new Users(id, balance, name, job, email);
                users.add(u);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }

    public Users getUser(int user_id) {
        SQLiteDatabase database = getWritableDatabase();
        // 3ayz a3ml search w ageb cars b id mo3ayan
        String[] args = {String.valueOf(user_id)};
        Cursor cursor = database.rawQuery("SELECT * FROM " + MyDatabase.DB_TABLE_1_NAME +
                " WHERE " +MyDatabase.DB_ID_COLUMN_NAME+ "=?", args);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabase.DB_ID_COLUMN_NAME));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_userNAME_COLUMN_NAME));
            String job = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_JOB_COLUMN_NAME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_EMAIL_COLUMN_NAME));
            long balance = cursor.getLong(cursor.getColumnIndexOrThrow(MyDatabase.DB_CURRENT_BALANCE_COLUMN_NAME));
            Users u = new Users(id, balance, name, job, email);
            cursor.close();
            return u;
        }
        return null;
    }

    public boolean insertTransaction(Users_transactions user) {
        //hna l key hya asm l column
        //getWritableDatabase() DE A7SAN FL WRITE
        //getReadableDatabase() DE A7SAN FL READ
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyDatabase.DB_FROM_COLUMN_NAME, user.getFrom_user());
        values.put(MyDatabase.DB_TO_COLUMN_NAME, user.getTo_user());
        values.put(MyDatabase.DB_AMOUNT_COLUMN_NAME, user.getAmount());
        long result = database.insert(MyDatabase.DB_TABLE_2_NAME, null, values);
        return result != -1;
    }
    public ArrayList<Users_transactions> getAllTransactions() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<Users_transactions> users_transactions = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + MyDatabase.DB_TABLE_2_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String user_from = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_FROM_COLUMN_NAME));
                String user_to = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.DB_TO_COLUMN_NAME));
                long amount = cursor.getLong(cursor.getColumnIndexOrThrow(MyDatabase.DB_AMOUNT_COLUMN_NAME));
                Users_transactions u = new Users_transactions(user_from, user_to, amount);
                users_transactions.add(u);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return users_transactions;
    }
}