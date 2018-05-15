package com.example.asus.iscan;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class Iscan extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iscan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_iscan, new Dashboard()).commit();

}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            fm.beginTransaction().replace(R.id.content_iscan, new Dashboard()).commit();
            Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {
            fm.beginTransaction().replace(R.id.content_iscan, new About()).commit();
            Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_help) {
            fm.beginTransaction().replace(R.id.content_iscan, new Description()).commit();
            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void onButtonClick(View view) {
        if (view.getId() == R.id.scan_btn) {
            Intent i = new Intent(Iscan.this, ScanActivity.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.runningapps_btn) {
            Intent i = new Intent(Iscan.this, MonitoringActivity.class);
            startActivity(i);
    }
        else if (view.getId()== R.id.button1) {
            Intent i = new Intent(Iscan.this, Notification.class);
            startActivity(i);
        }
    }}
