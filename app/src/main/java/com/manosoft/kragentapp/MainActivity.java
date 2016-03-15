package com.manosoft.kragentapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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

    }

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
        fragmentTransaction.add(R.id.main_container, new PolicyFragment(),policyScreen);
        fragmentTransaction.commit();
        currentFragmentTag=policyScreen;
        getSupportActionBar().setTitle(R.string.policy_fragment);

        //Set the Listener to NavigationView//
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setCheckedItem(R.id.nav_policy);
        navigationView.setNavigationItemSelectedListener(navigationViewListener);

    }

    public void _initialiseDatabase(){
        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();
    }
    public boolean _displaySelectedFragment(int viewID) {

        boolean fragmentDisplayedSuccessfully = false;
        Fragment fragment;

        assert getSupportActionBar() != null;
        switch (viewID) {
            case R.id.nav_policy:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new PolicyFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.policy_fragment);
                fragmentDisplayedSuccessfully = true;
                break;

            case R.id.nav_commission:
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new CommissionFragment());
                fragmentTransaction.commit();
                getSupportActionBar().setTitle(R.string.commission_fragment);
                fragmentDisplayedSuccessfully = true;
                break;

            case R.id.nav_setting:
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

    public NavigationView.OnNavigationItemSelectedListener navigationViewListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            boolean result;
            result = _displaySelectedFragment(item.getItemId());
            if (result){
                item.setChecked(true);
            }
            drawerLayout.closeDrawers();
            return false;
        }
    };


}

