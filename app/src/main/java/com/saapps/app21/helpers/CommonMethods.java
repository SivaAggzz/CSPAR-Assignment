package com.saapps.app21.helpers;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.saapps.app21.R;

public class CommonMethods {

    public static void displayShortToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }

    public static void loadImage(ImageView imageView,int imageRES){
        Glide.with(imageView.getContext()).load(imageRES).into(imageView);
    }

    public static void loadRoundedImage(ImageView imageView,String imageURL){
        Glide.with(imageView.getContext()).load(imageURL)
                .circleCrop()
                .placeholder(R.color.nav_bg_color)
                .into(imageView);

    }
}
