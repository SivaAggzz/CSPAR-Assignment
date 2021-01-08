package com.saapps.app21.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.saapps.app21.listeners.BottomNavigationItemSelectedListener;
import com.saapps.app21.R;
import com.saapps.app21.adapters.MainViewPagerAdapter;
import com.saapps.app21.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeMethods();
    }

    private void initializeMethods() {
        initializeUI();
        initializeToolbar();
        initializeBottomNavigation();
        initializePager();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));
    }

    private void initializeUI() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    private void initializeToolbar() {
        mainBinding.toolbar.setText(R.string.cspar_assignment);
    }

    private void initializeBottomNavigation() {
        BottomNavigationItemSelectedListener bottomNavigationItemSelectedListener = new BottomNavigationItemSelectedListener(mainBinding);
        mainBinding.bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationItemSelectedListener);
    }

    private void initializePager() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainBinding.mainViewPager.setPagingEnabled(false);
        mainBinding.mainViewPager.setAdapter(mainViewPagerAdapter);
        mainBinding.mainViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}