package com.project1.cs496_project1;

import android.net.Uri;


public class Contact {
    public Uri profilePicture;
    public String name;
    public String phoneNumber;

    public void setProfilePicture(Uri profilePicture) {
        profilePicture = profilePicture;
    }
    public void setName(String name) {
        name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber;
    }

    public Uri getProfilePicture() {
        return this.profilePicture;
    }
    public String getName() {
        return this.name;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }


}
