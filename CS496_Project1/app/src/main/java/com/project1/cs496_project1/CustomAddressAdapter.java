package com.project1.cs496_project1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        profileImageView.setImageURI(listViewItem.profilePicture);
        nameTextView.setText(listViewItem.name);
        phoneNumberTextView.setText(listViewItem.phoneNumber);

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

    public void addItem(Uri profilePicture, String name, String phoneNumber) {
        Contact item = new Contact();

        item.profilePicture = profilePicture;
        item.name = name;
        item.phoneNumber = phoneNumber;

        contactViewItemList.add(item);
    }


}
