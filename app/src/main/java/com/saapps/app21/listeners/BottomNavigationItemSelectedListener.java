package com.saapps.app21.listeners;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.saapps.app21.R;
import com.saapps.app21.databinding.ActivityMainBinding;

public class BottomNavigationItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ActivityMainBinding mainBinding;

    public BottomNavigationItemSelectedListener(ActivityMainBinding mainBinding) {
        this.mainBinding = mainBinding;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home: {
                mainBinding.mainViewPager.setCurrentItem(0);
                mainBinding.bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                break;
            }
            case R.id.menu_profile: {
                mainBinding.mainViewPager.setCurrentItem(1);
                mainBinding.bottomNavigationView.getMenu().findItem(R.id.menu_profile).setChecked(true);
                break;
            }
            case R.id.menu_about: {
                mainBinding.mainViewPager.setCurrentItem(2);
                mainBinding.bottomNavigationView.getMenu().findItem(R.id.menu_about).setChecked(true);
                break;
            }
        }
        return false;
    }
}
