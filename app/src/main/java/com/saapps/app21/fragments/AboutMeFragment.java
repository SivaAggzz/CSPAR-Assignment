package com.saapps.app21.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.saapps.app21.R;
import com.saapps.app21.databinding.FragmentAboutBinding;

public class AboutMeFragment  extends Fragment {

    public AboutMeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentAboutBinding fragmentAboutBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
        return fragmentAboutBinding.getRoot();
    }

}