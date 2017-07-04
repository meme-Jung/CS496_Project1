package com.project1.cs496_project1;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class DisplayAddressActivity extends AppCompatActivity {
    // crawling contacts.
    // combine name and phoneNumber and add the ArrayList
    // finally return the ArrayList<String>
    public void addContacts(CustomAddressAdapter adapter) {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String contactId = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
//            Integer photoId = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
            Uri profilePicture = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));

            adapter.addItem(Uri.withAppendedPath(profilePicture, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY), name, phoneNumber, contactId);
        }

        phones.close();
    }


    // when this show up create the ContentView and call addContacts() function to collect name and phone-number
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_address);

        CustomAddressAdapter adapter = new CustomAddressAdapter();//(getApplicationContext(), R.layout.address_row, addContacts());
        addContacts(adapter);

        ListView listview = (ListView) findViewById(R.id.listView1);
        listview.setAdapter(adapter);

    }

    // 누르면 전화하기
    public void callWithNumber(View view){
        TextView phoneNumberTextView = (TextView) view.findViewById(R.id.phoneNumber);
        String phoneNumber = String.valueOf(phoneNumberTextView.getText());
        startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)));


    }

}
