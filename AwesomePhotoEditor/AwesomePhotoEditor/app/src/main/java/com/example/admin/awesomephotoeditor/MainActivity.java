package com.example.admin.awesomephotoeditor;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    fragmentImageD f1;
    Bundle extras = null;
    boolean var = false;
    ViewPager viewPager;
    CustomSwipAdapter adapter;
    boolean conditionImage = false;

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else if (viewPager.getCurrentItem() == 3) {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 4000);

        adapter = new CustomSwipAdapter(this);
        viewPager.setAdapter(adapter);



        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); */

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }


    }



    public void attachFragmentCameraDisplay(Bundle extras) {
        android.app.FragmentManager fm = getFragmentManager();
        this.extras = extras;
        android.app.FragmentTransaction ft = fm.beginTransaction();
        conditionImage = true;
        fragmentImageD f1 = (fragmentImageD) fm.findFragmentByTag("fd");
        System.out.println("in the fragment image out");
        if (f1 == null) {
            System.out.println("in the fragment image in");
            f1 = new fragmentImageD();
            f1.setArguments(extras);
            ft.replace(R.id.fragment, f1, "fd");
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
            System.out.println("in but after commit");
        }
        else{
            System.out.println("in the else part");

            f1.sendData(extras);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mybutton) {
            if(conditionImage == true) {
                android.app.FragmentManager fm = getFragmentManager();

                android.app.FragmentTransaction ft = fm.beginTransaction();

                fragmentImageD f1 = (fragmentImageD) fm.findFragmentByTag("fd");
                f1.saveImage();
                item.setEnabled(false);
                conditionImage = false;
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            attachFragment1();

        } else if (id == R.id.nav_edit) {
            attachFragmentCameraDisplay(extras);

        } else if (id == R.id.nav_effects) {
            attachFragmentHome();

        } else if (id == R.id.nav_quit) {
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void attachFragmentHome() {
        android.app.FragmentManager fm = getFragmentManager();

        android.app.FragmentTransaction ft = fm.beginTransaction();

        fragmentHome f = (fragmentHome) fm.findFragmentByTag("fm");

        if (f == null) {
            f = new fragmentHome();
            ft.replace(R.id.fragment, f, "fm");
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }

    }
    public void attachFragment1() {
        android.app.FragmentManager fm = getFragmentManager();

        android.app.FragmentTransaction ft = fm.beginTransaction();

        fragment1 f2 = (fragment1) fm.findFragmentByTag("f1");

        if (f2 == null) {
            f2 = new fragment1();
            ft.replace(R.id.fragment, f2, "f1");
            ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        }


    }


}
