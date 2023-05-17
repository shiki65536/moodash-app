package com.example.moodapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.moodapp.databinding.ActivityFrontBinding;
import com.example.moodapp.utility.BackUpWorker;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FrontActivity extends AppCompatActivity {
    private ActivityFrontBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFrontBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.appBar.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_fragment, R.id.nav_mood_fragment, R.id.nav_record_fragment, R.id.nav_map_fragment, R.id.nav_collection_fragment)
                .setOpenableLayout(binding.drawerLayout)
                .build();
        //to display the Navigation button as a drawer symbol,not being shown as an Up button
        FragmentManager fragmentManager= getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)
        fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //Sets up a NavigationView for use with a NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Sets up a Toolbar for use with a NavController.
        NavigationUI.setupWithNavController(binding.appBar.toolbar,navController,
        mAppBarConfiguration);


        /** WORK MANAGER **/
        //Periodic
        PeriodicWorkRequest periodicRequest =
                new PeriodicWorkRequest.Builder(BackUpWorker.class,24, TimeUnit.HOURS,
                        15, TimeUnit.MINUTES)
                        .build();
        WorkManager.getInstance(this).enqueue(periodicRequest);

        //One time
        //create nav view & get menu
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem workManagerMenuItem = menu.findItem(R.id.nav_work_manager_button);
        //Click event
        workManagerMenuItem.setOnMenuItemClickListener(
                new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                toastMsg("Execute work manager!");
                WorkRequest oneTimeRequest =
                        new OneTimeWorkRequest.Builder(BackUpWorker.class)
                                .build();
                WorkManager
                        .getInstance(getApplicationContext())
                        .enqueue(oneTimeRequest);
                return true;
            }
        });
    }

    public void toastMsg(String message){
        Context context = this;
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
