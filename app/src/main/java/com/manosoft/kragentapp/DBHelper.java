package com.manosoft.kragentapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VenkataManohar on 13/03/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    /*Database name and version number*/
    private static final String DBNAME = "krdb.db";
    private static final int VERSION = 1;

    /*Table name in the database*/
    private static final String CUSTOMER_TABLE = "customerTbl";
    private static final String POLICY_TABLE = "policyTbl";
    private static final String COMMISSION_TABLE = "commissionTbl";

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
