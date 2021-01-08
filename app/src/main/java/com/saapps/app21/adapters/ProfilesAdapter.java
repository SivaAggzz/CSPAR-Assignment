package com.saapps.app21.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saapps.app21.R;
import com.saapps.app21.adapters.viewholder.ProfileViewHolder;
import com.saapps.app21.databinding.SingleProfileItemBinding;
import com.saapps.app21.helpers.CommonMethods;
import com.saapps.app21.models.ProfileObj;
import com.saapps.app21.view.ProfileViewer;

import java.util.ArrayList;

public class ProfilesAdapter extends RecyclerView.Adapter<ProfileViewHolder> {
    private Context context;
    private ArrayList<ProfileObj> profiles;
    private LayoutInflater layoutInflater;

    public ProfilesAdapter(Context context, ArrayList<ProfileObj> profiles) {
        this.context = context;
        this.profiles = profiles;
        layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SingleProfileItemBinding singleProfileItemBinding= DataBindingUtil.inflate(layoutInflater, R.layout.single_profile_item,parent,false);
        return new ProfileViewHolder(singleProfileItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int position) {
        profileViewHolder.getProfileItemBinding().profileName.setText(profiles.get(position).getName());
        profileViewHolder.getProfileItemBinding().profileDesc.setText(profiles.get(position).getDescription());

        CommonMethods.loadRoundedImage(profileViewHolder.getProfileItemBinding().profileIcon, profiles.get(position).getImage());

        profileViewHolder.itemView.setOnClickListener(v -> {
            Intent profileViewerIntent=new Intent(context, ProfileViewer.class);
            profileViewerIntent.putExtra(context.getString(R.string.profile_data),profiles.get(position));
            context.startActivity(profileViewerIntent);
        });
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }
}
