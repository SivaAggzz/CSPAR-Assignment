package com.saapps.app21.view;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.saapps.app21.R;
import com.saapps.app21.databinding.ActivityProfileViewerBinding;
import com.saapps.app21.helpers.CommonMethods;
import com.saapps.app21.models.ProfileObj;

import java.util.Objects;

public class ProfileViewer extends AppCompatActivity {
    private ActivityProfileViewerBinding profileViewerBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeMethods();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.blueA400));
    }

    private void initializeMethods() {
        initializeUI();
        initializeProfile();
    }

    private void initializeUI() {
        profileViewerBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_viewer);
    }


    private void initializeProfile() {
        ProfileObj currentProfileObject = Objects.requireNonNull(getIntent().getExtras()).getParcelable(getString(R.string.profile_data));
        assert currentProfileObject != null;

        CommonMethods.loadRoundedImage(profileViewerBinding.profileIcon,currentProfileObject.getImage());
        profileViewerBinding.profileName.setText(currentProfileObject.getName());
        profileViewerBinding.profileContact.setSummary(currentProfileObject.getContact());
        profileViewerBinding.profileId.setSummary(currentProfileObject.getId());
        profileViewerBinding.profileCategory.setSummary(currentProfileObject.getCategory());
        profileViewerBinding.profileCategoryId.setSummary(currentProfileObject.getCategoryid());
        profileViewerBinding.profileAddress.setSummary(currentProfileObject.getAddress());
        profileViewerBinding.profileDescription.setSummary(currentProfileObject.getDescription());
        profileViewerBinding.profileEmployeeCode.setSummary(currentProfileObject.getEmpcode());
    }

    @SuppressWarnings("unused")
    public void onBackButtonClicked(View view) {
        finish();
    }
}