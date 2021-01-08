package com.saapps.app21.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileObj implements Parcelable {
    private String id;
    private String name;
    private String category;
    private String categoryid;
    private String address;
    private String description;
    private String contact;
    private String empcode;
    private String image;

    public ProfileObj() {
    }

    public static ProfileObj createProfileFromJSON(JSONObject profileJSONObj) throws JSONException {
        ProfileObj profileObj = new ProfileObj();
        profileObj.setId(profileJSONObj.getString("id"));
        profileObj.setName(profileJSONObj.getString("name"));
        profileObj.setCategory(profileJSONObj.getString("category"));
        profileObj.setCategoryid(profileJSONObj.getString("categoryid"));
        profileObj.setAddress(profileJSONObj.getString("address"));
        profileObj.setDescription(profileJSONObj.getString("description"));
        profileObj.setContact(profileJSONObj.getString("contact"));
        profileObj.setEmpcode(profileJSONObj.getString("empcode"));
        profileObj.setImage(profileJSONObj.getString("image"));
        return profileObj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmpcode() {
        return empcode;
    }

    public void setEmpcode(String empcode) {
        this.empcode = empcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.category);
        dest.writeString(this.categoryid);
        dest.writeString(this.address);
        dest.writeString(this.description);
        dest.writeString(this.contact);
        dest.writeString(this.empcode);
        dest.writeString(this.image);
    }

    protected ProfileObj(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.category = in.readString();
        this.categoryid = in.readString();
        this.address = in.readString();
        this.description = in.readString();
        this.contact = in.readString();
        this.empcode = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<ProfileObj> CREATOR = new Parcelable.Creator<ProfileObj>() {
        @Override
        public ProfileObj createFromParcel(Parcel source) {
            return new ProfileObj(source);
        }

        @Override
        public ProfileObj[] newArray(int size) {
            return new ProfileObj[size];
        }
    };
}
