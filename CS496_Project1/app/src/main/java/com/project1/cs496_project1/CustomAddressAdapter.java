package com.project1.cs496_project1;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class CustomAddressAdapter extends BaseAdapter {
    private ArrayList<Contact> contactViewItemList = new ArrayList<Contact>();

    public CustomAddressAdapter() {

    }

    @Override
    public int getCount() {
        return contactViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.address_row, parent, false);
        }

        ImageView profileImageView = (ImageView) convertView.findViewById(R.id.photo);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        TextView phoneNumberTextView = (TextView) convertView.findViewById(R.id.phoneNumber);

        Contact listViewItem = contactViewItemList.get(position);

        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(listViewItem.getContactId()));
        InputStream photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri, true);
        Log.i("photoId", String.valueOf(listViewItem.getPhotoId()));
        Log.i("name", listViewItem.getName());
        Log.i("photo_stream", listViewItem.getName());
        if (listViewItem.getPhotoId() != null)
            profileImageView.setImageBitmap(BitmapFactory.decodeStream(photo_stream));


//        profileImageView.setImageURI(listViewItem.getProfilePicture());
        nameTextView.setText(listViewItem.getName());
        phoneNumberTextView.setText(listViewItem.getPhoneNumber());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return contactViewItemList.get(position) ;
    }

    public void addItem(Uri profilePicture, String name, String phoneNumber, String contactId, Integer photoId) {
        Contact item = new Contact();
        item.setProfilePicture(profilePicture);
        item.setName(name);
        item.setPhoneNumber(phoneNumber);
        item.setContactId(contactId);
        item.setPhotoId(photoId);

        contactViewItemList.add(item);
    }



}
