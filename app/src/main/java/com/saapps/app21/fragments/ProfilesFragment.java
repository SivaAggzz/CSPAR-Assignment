package com.saapps.app21.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.saapps.app21.viewmodel.HomeScreenViewModel;
import com.saapps.app21.R;
import com.saapps.app21.adapters.ProfilesAdapter;
import com.saapps.app21.databinding.FragmentProfilesBinding;

public class ProfilesFragment extends Fragment {
    private FragmentProfilesBinding fragmentProfilesBinding;
    private ProfilesAdapter profilesAdapter;

    private HomeScreenViewModel homeScreenViewModel;

    public ProfilesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentProfilesBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_profiles, container, false);
        initializeMethods();
        return fragmentProfilesBinding.getRoot();
    }

    private void initializeMethods() {
        initializeViewModel();
        initializeProfiles();
    }

    private void initializeViewModel() {
        homeScreenViewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
    }

    private void initializeProfiles() {
        homeScreenViewModel.getProfileDataFromAPI().observe(getViewLifecycleOwner(), profileObjs -> {
            profilesAdapter=new ProfilesAdapter(getContext(),profileObjs);
            fragmentProfilesBinding.profilesView.setAdapter(profilesAdapter);
        });
    }
}
