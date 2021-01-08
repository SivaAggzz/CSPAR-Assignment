package com.saapps.app21.adapters.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saapps.app21.databinding.SingleProfileItemBinding;

public class ProfileViewHolder extends RecyclerView.ViewHolder {
    private SingleProfileItemBinding profileItemBinding;

    public ProfileViewHolder(@NonNull SingleProfileItemBinding profileItemBinding) {
        super(profileItemBinding.getRoot());
        setProfileItemBinding(profileItemBinding);
    }

    public SingleProfileItemBinding getProfileItemBinding() {
        return profileItemBinding;
    }

    public void setProfileItemBinding(SingleProfileItemBinding profileItemBinding) {
        this.profileItemBinding = profileItemBinding;
    }
}
