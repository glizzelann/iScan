package com.example.asus.iscan;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class Dashboard extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard, container, false);

        return rootView;
    }


    public class Iscan extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_iscan);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            final Animation animTranslate = AnimationUtils.loadAnimation(this,R.anim.anin_translate);

            ImageButton runningapps_btn = (ImageButton) findViewById(R.id.runningapps_btn);
            ImageButton scan_btn = (ImageButton) findViewById(R.id.scan_btn);
            scan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.startAnimation(animTranslate);
                    Intent i = new Intent(Iscan.this, ScanActivity.class);
                    startActivity(i);
                }
            });


            /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
             fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
            }
            });**/

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

        /** @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.iscan, menu);
        return true;
        }**/

        /**
         * @Override public boolean onOptionsItemSelected(MenuItem item) {
         * // Handle action bar item clicks here. The action bar will
         * // automatically handle clicks on the Home/Up button, so long
         * // as you specify a parent activity in AndroidManifest.xml.
         * int id = item.getItemId();
         * <p>
         * //noinspection SimplifiableIfStatement
         * if (id == R.id.action_settings) {
         * return true;
         * }
         * <p>
         * return super.onOptionsItemSelected(item);
         * }
         **/

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

            }
            else if (view.getId() == R.id.runningapps_btn) {
                Intent i = new Intent(Iscan.this, Monitoring.class);
                startActivity(i);
            }

        }}}
