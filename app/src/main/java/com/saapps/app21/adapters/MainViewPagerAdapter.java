package com.saapps.app21.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.saapps.app21.fragments.AboutMeFragment;
import com.saapps.app21.fragments.HomeFragment;
import com.saapps.app21.fragments.ProfilesFragment;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new HomeFragment();
            case 1:return new ProfilesFragment();
            case 2:return new AboutMeFragment();

        }
        return new HomeFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
