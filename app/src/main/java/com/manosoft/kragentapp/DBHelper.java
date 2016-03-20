package com.manosoft.kragentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VenkataManohar on 13/03/2016.
 * Database helper class to do CRUD database operations on SQLite DB
 */
public class DBHelper extends SQLiteOpenHelper {

    /*Database name and version number*/
    private static final String DBNAME = "krdb.db";
    private static final int VERSION = 1;

    /*Table name in the database*/
    public final String CUSTOMER_TABLE = "customerTbl";
    public final String POLICY_TABLE = "policyTbl";
    public final String COMMISSION_TABLE = "commissionTbl";

    /**
     * Column name of the Customer Table
     */
    public final String CuT_id = "_id";
    public final String CuT_customerId = "customerId";
    public final String CuT_customerName = "customerName";
    public final String CuT_hAddress = "hAddress";
    public final String CuT_hCity = "hCity";
    public final String CuT_hPin = "hPin";
    public final String CuT_hEmail = "hEmail";
    public final String CuT_hPhone = "hPhone";
    public final String CuT_hMobile = "hMobile";
    public final String CuT_bAddress = "bAddress";
    public final String CuT_bCity = "bCity";
    public final String CuT_bEmail = "bEmail";
    public final String CuT_bPhone = "bPhone";
    public final String CuT_bMobile = "bMobile";
    public final String CuT_customerStatus = "customerStatus";

    /**
     * Column name of the Policy Table
     */
    public final String PoT_id = "_id";
    public final String PoT_policyId = "policyId";
    public final String PoT_customerId = "customerId";
    public final String PoT_policyHolder = "policyHolder";
    public final String PoT_applicantName = "applicantName";
    public final String PoT_dateOfBirth = "dateOfBirth";
    public final String PoT_age = "age";
    public final String PoT_gender = "gender";
    public final String PoT_policyDate = "policyDate";
    public final String PoT_policyScheme = "policyScheme";
    public final String PoT_policyPeriod = "policyPeriod";
    public final String PoT_modeOfPayment = "modeOfPayment";
    public final String PoT_sumAssured = "sumAssured";
    public final String PoT_premiumAmount = "premiumAmount";
    public final String PoT_maturityAmount = "maturityAmount";
    public final String PoT_nomineeName = "nomineeName";
    public final String PoT_comments = "comments";
    public final String PoT_policyStatus = "policyStatus";

    /**
     * Column name of the Commission Table
     */
    public final String CoT_id = "_id";
    public final String CoT_dateCommissionReceived = "dateCommissionReceived";
    public final String CoT_commissionRate = "commissionRate";
    public final String CoT_commissionEligible = "commissionEligible";
    public final String CoT_commissionReceived = "commissionReceived";
    public final String CoT_taxDeducted = "taxDeducted";
    public final String CoT_netCommission = "netCommission";
    public final String CoT_commissionPending = "commissionPending";
    public final String CoT_recordLoggedDateTime = "recordLoggedDateTime";

    public SQLiteDatabase krDB;


    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQLQuery = "CREATE TABLE " + CUSTOMER_TABLE +
                " ( " +
                CuT_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CuT_customerId + " TEXT NOT NULL, " +
                CuT_customerName + " TEXT NOT NULL, " +
                CuT_hAddress + " TEXT NOT NULL, " +
                CuT_hCity + " TEXT NOT NULL, " +
                CuT_hPin + " TEXT NOT NULL, " +
                CuT_hEmail + " TEXT NOT NULL, " +
                CuT_hPhone + " TEXT NOT NULL, " +
                CuT_hMobile + " TEXT NOT NULL, " +
                CuT_bAddress + " TEXT NOT NULL, " +
                CuT_bCity + " TEXT NOT NULL, " +
                CuT_bEmail + " TEXT NOT NULL, " +
                CuT_bPhone + " TEXT NOT NULL, " +
                CuT_bMobile + " TEXT NOT NULL, " +
                CuT_customerStatus + " TEXT NOT NULL " +
                ")";
        db.execSQL(SQLQuery);

        SQLQuery = "CREATE TABLE " + POLICY_TABLE +
                " (" +
                PoT_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PoT_policyId + " TEXT NOT NULL, " +
                PoT_customerId + " TEXT NOT NULL, " +
                PoT_policyHolder + " TEXT NOT NULL, " +
                PoT_applicantName + " TEXT, " +
                PoT_dateOfBirth + " TEXT NOT NULL, " +
                PoT_age + " INTEGER NOT NULL, " +
                PoT_gender + " TEXT NOT NULL, " +
                PoT_policyDate + " TEXT NOT NULL, " +
                PoT_policyScheme + " TEXT NOT NULL, " +
                PoT_policyPeriod + " INTEGER NOT NULL, " +
                PoT_modeOfPayment + " TEXT NOT NULL, " +
                PoT_sumAssured + " REAL NOT NULL, " +
                PoT_premiumAmount + " REAL NOT NULL, " +
                PoT_maturityAmount + " REAL NOT NULL, " +
                PoT_nomineeName + " TEXT NOT NULL, " +
                PoT_comments + " TEXT, " +
                PoT_policyStatus + " TEXT NOT NULL " +
                ")";
        db.execSQL(SQLQuery);

        SQLQuery = "CREATE TABLE " + COMMISSION_TABLE +
                " (" +
                CoT_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CoT_dateCommissionReceived + " TEXT NOT NULL, " +
                CoT_commissionRate + " TEXT NOT NULL, " +
                CoT_commissionEligible + " TEXT NOT NULL, " +
                CoT_commissionReceived + " TEXT NOT NULL, " +
                CoT_taxDeducted + " TEXT NOT NULL, " +
                CoT_netCommission + " TEXT NOT NULL, " +
                CoT_commissionPending + " TEXT NOT NULL, " +
                CoT_recordLoggedDateTime + " TEXT NOT NULL " +
                ")";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void OpenDB() {
        krDB = getWritableDatabase();
    }

    public void CloseDB() {
        if (krDB != null && krDB.isOpen()) {
            krDB.close();
        }
    }

    public Long insertTableRow(String tableName, ContentValues values) {

        return krDB.insert(tableName, null, values);
    }

    public Cursor getAllRecords(String tableName){

//        Cursor cursor = krDB.query(tableName,columns,null,null,null,null,null);
        Cursor cursor = krDB.rawQuery("select * from " + tableName,null);
        if (cursor != null) cursor.moveToFirst();
        return cursor;
    }


}
