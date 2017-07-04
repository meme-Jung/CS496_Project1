package com.project1.cs496_project1;

import android.net.Uri;


public class Contact {
    private Uri profilePicture;
    private String name;
    private String phoneNumber;
    private String contactId;
    private Integer photoId;

    public void setProfilePicture(Uri profilePicture) { this.profilePicture = profilePicture; }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setContactId(String contactId) { this.contactId = contactId; }
    public void setPhotoId(Integer photoId) { this.photoId = photoId; }

    public Uri getProfilePicture() {
        return this.profilePicture;
    }
    public String getName() {
        return this.name;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getContactId() {
        return this.contactId;
    }
    public Integer getPhotoId() { return this.photoId; }

}
