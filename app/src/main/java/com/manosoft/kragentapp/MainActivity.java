package com.manosoft.kragentapp;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    public DBHelper dbHelper;
    public static final String policyScreen = "policyScreen";
    public static final String commissionScreen = "commissionScreen";
    public static final String settingScreen = "settingsScreen";
    String currentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Layouts and Database//
        _initialiseLayoutViews();
        _initialiseDatabase();
//        _insertCustomerRow();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);

        //Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
//        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("CommitTransaction")
    public void _initialiseLayoutViews() {

        //Initialise toolbar to application action bar//
        toolbar = (Toolbar) findViewById(R.id.toolbar_actionBar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;

        //Initialise drawerLayout and add listener//
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.syncState();

        //Initialise FragmentTransaction and set the first page to Policy page//
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, new PolicyFragment(), policyScreen);
        fragmentTransaction.commit();
        currentFragmentTag = policyScreen;
        getSupportActionBar().setTitle(R.string.policy_fragment);

        //Set the Listener to NavigationView//
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        assert navigationView != null;
        navigationView.setCheckedItem(R.id.nav_policy);
        navigationView.setNavigationItemSelectedListener(navigationViewListener);

    }

    public void _initialiseDatabase() {
        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();
    }

    public void _insertCustomerRow() {

        Long returnValue;

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.CuT_customerId, "1");
        contentValues.put(dbHelper.CuT_customerName, "Tester");
        contentValues.put(dbHelper.CuT_hAddress, "42 Broadhurst Place");
        contentValues.put(dbHelper.CuT_hCity, "Basildon");
        contentValues.put(dbHelper.CuT_hPin, "SS142FQ");
        contentValues.put(dbHelper.CuT_hEmail, "kvmanohar@gmail.com");
        contentValues.put(dbHelper.CuT_hPhone, "01268907091");
        contentValues.put(dbHelper.CuT_hMobile, "07951625543");
        contentValues.put(dbHelper.CuT_bAddress, "Test address");
        contentValues.put(dbHelper.CuT_bCity, "London");
        contentValues.put(dbHelper.CuT_bEmail, "i.tescobank.com");
        contentValues.put(dbHelper.CuT_bPhone, "0207369603");
        contentValues.put(dbHelper.CuT_bMobile, "07756974180");
        contentValues.put(dbHelper.CuT_customerStatus, "Active");
        returnValue = dbHelper.insertTableRow(dbHelper.CUSTOMER_TABLE, contentValues);

        if (returnValue == -1) {
            Toast.makeText(getApplicationContext(),"Error inserting Row",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Successfully inserted Row",Toast.LENGTH_SHORT).show();
        }

    }

    public DBHelper returndbHelper(){
        return dbHelper;
    }

    @SuppressLint("CommitTransaction")
    public boolean _displaySelectedFragment(int viewID) {

        boolean fragmentDisplayedSuccessfully = false;
//        Fragment fragment;

        assert getSupportActionBar() != null;
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (viewID) {
            case R.id.nav_customer:
                fragmentTransaction.replace(R.id.main_container, new CustomerFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.customer_fragment);
                fragmentDisplayedSuccessfully = true;
                break;

            case R.id.nav_policy:
                fragmentTransaction.replace(R.id.main_container, new PolicyFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.policy_fragment);
                fragmentDisplayedSuccessfully = true;
                break;

            case R.id.nav_commission:
                fragmentTransaction.replace(R.id.main_container, new CommissionFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.commission_fragment);
                fragmentDisplayedSuccessfully = true;
                break;

            case R.id.nav_setting:
                fragmentTransaction.replace(R.id.main_container, new SettingsFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.settings_fragment);
                fragmentDisplayedSuccessfully = true;
                break;
        }

        return fragmentDisplayedSuccessfully;
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.OpenDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.CloseDB();
    }


    public NavigationView.OnNavigationItemSelectedListener navigationViewListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            boolean result;
            result = _displaySelectedFragment(item.getItemId());
            if (result) {
                item.setChecked(true);
            }
            drawerLayout.closeDrawers();
            return false;
        }
    };


}

