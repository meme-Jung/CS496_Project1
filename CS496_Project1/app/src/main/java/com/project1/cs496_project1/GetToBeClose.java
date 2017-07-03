package com.project1.cs496_project1;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GetToBeClose extends AppCompatActivity {

    private ArrayList<Contact> contactArrayList = new ArrayList<>();

    public ImageView gtbcImageView = (ImageView) findViewById(R.id.gtbc_photo);
    public Button gtbcButton0 = (Button) findViewById(R.id.gtbc_button_0);
    public Button gtbcButton1 = (Button) findViewById(R.id.gtbc_button_1);
    public Button gtbcButton2 = (Button) findViewById(R.id.gtbc_button_2);
    public Button gtbcButton3 = (Button) findViewById(R.id.gtbc_button_3);
    public Button gtbcButton4 = (Button) findViewById(R.id.gtbc_button_4);
    public Button gtbcButton5 = (Button) findViewById(R.id.gtbc_button_5);

    private int gtbcTotalScore = 0;
    private int gtbcScore = 0;

    // crawling contacts.
    // combine name and phoneNumber and add the ArrayList
    // finally return the ArrayList<String>
    public void fillContactArrayListRandomly() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {
            Contact c = new Contact();

            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String contactId = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            Uri profilePicture = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));

            c.setName(name);
            c.setPhoneNumber(phoneNumber);
            c.setProfilePicture(profilePicture);

            contactArrayList.add(c);
        }
        phones.close();

//        long seed = System.nanoTime();
        Collections.shuffle(contactArrayList);
    }

    // check the pressed button's name match with the photo
    // if right answer return true, else return false;
    public boolean checkWriteAnswer() {
        return true;
    }

    // setting button's text by names randomly, but must be included correct name
    public void gtbcSettingButton() {

        Random random = new Random();

        gtbcButton0.setText(contactArrayList.get(random.nextInt(gtbcTotalScore)).getName());
        gtbcButton1.setText(contactArrayList.get(random.nextInt(gtbcTotalScore)).getName());
        gtbcButton2.setText(contactArrayList.get(random.nextInt(gtbcTotalScore)).getName());
        gtbcButton3.setText(contactArrayList.get(random.nextInt(gtbcTotalScore)).getName());
        gtbcButton4.setText(contactArrayList.get(random.nextInt(gtbcTotalScore)).getName());
        gtbcButton5.setText(contactArrayList.get(random.nextInt(gtbcTotalScore)).getName());

    }


    // call number
    public void callByNumber(String phoneNumber) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillContactArrayListRandomly();
        gtbcTotalScore = contactArrayList.size();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_to_be_close);

        gtbcImageView.setImageURI(contactArrayList.get(gtbcScore).getProfilePicture());
        gtbcSettingButton();
        gtbcScore++;
        
//        do {
//            gtbcImageView.setImageURI(contactArrayList.get(gtbcScore).getProfilePicture());
//            gtbcSettingButton();
//            gtbcScore++;
//
//        } while (checkWriteAnswer() && gtbcScore <= gtbcTotalScore);

        // get all right answer
        if (gtbcScore == gtbcTotalScore && checkWriteAnswer()) {

        } else { // wrong answer
            callByNumber(contactArrayList.get(gtbcScore).getPhoneNumber());
        }

    }
}
