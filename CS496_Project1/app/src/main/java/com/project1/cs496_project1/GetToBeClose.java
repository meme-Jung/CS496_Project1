package com.project1.cs496_project1;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.util.*;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.security.AccessController.getContext;

public class GetToBeClose extends AppCompatActivity {

    private ArrayList<Contact> contactArrayList = new ArrayList<>();


    private int gtbcTotalScore = 0;
    private int gtbcScore = 0;

    // crawling contacts.
    // combine name and phoneNumber and add the ArrayList
    // finally return the ArrayList<String>
    public void fillContactArrayListRandomly() {
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (phones.moveToNext()) {

            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String contactId = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            Integer photoId = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
//            Log.i("photo", String.valueOf(photoId));
            if (photoId == 0) { // there is no contact picture
                continue;
            }

            Contact c = new Contact();

            c.setName(name);
            c.setPhoneNumber(phoneNumber);
            c.setContactId(contactId);

            contactArrayList.add(c);
        }
        phones.close();

        Collections.shuffle(contactArrayList);
    }
    // check the pressed button's name match with the photo
    //
    // 맞는지 틀린지 확인해서 맞으면 rightButton, 틀리면 wrongButton 실행;
    public void checkRightAnswer(View view) {
        String buttonText = ((Button) view).getText().toString();
        if (buttonText == contactArrayList.get(gtbcScore).getName()) { // same name
            rightButton();
        } else {        // wrong answer button pressed
            wrongButton(contactArrayList.get(gtbcScore).getPhoneNumber());
        }
    }

    // 맞는 버튼이 눌렸을 때
    public void rightButton(){
        gtbcScore += 1;
        gtbcSettingScoreBoard();
        if(gtbcScore == gtbcTotalScore){
            Intent intent = new Intent(this, FinalGamePage.class);
            startActivity(intent);
            finish();
        }
        else{
            gtbcStart();
        }
    }

    // 틀린 버튼이 눌렸을 때
    public void wrongButton(String phoneNumber){
        Intent intent = new Intent(this, GameOverPage.class);
        startActivity(intent);
        startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)));
//        startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNumber)));
        finish();
    }


    public ArrayList<Integer> randomNumberGet(int mustIncludedInt, int boundInt) {
        Random random = new Random();

        ArrayList<Integer> randomNumberList = new ArrayList<>();
        randomNumberList.add(mustIncludedInt);
        while (randomNumberList.size() < 6) {
            Integer randomNumber =  random.nextInt(boundInt);
            if (!randomNumberList.contains(randomNumber))
                randomNumberList.add(randomNumber);
        }
        Collections.shuffle(randomNumberList);
        return randomNumberList;
    }


    public void gtbcSettingScoreBoard() {
        TextView gtbcScoreTextView = (TextView) findViewById(R.id.gtbcScoreTextView);
        gtbcScoreTextView.setText(String.valueOf(gtbcScore));
    }

    public void gtbcSettingTotalScoreBoard() {
        TextView gtbcTotalScoreTextView = (TextView) findViewById(R.id.gtbcTotalScoreTextView);
        gtbcTotalScoreTextView.setText(String.valueOf(gtbcTotalScore));
    }


    // setting button's text by names randomly, but must be included correct name
    public void gtbcSettingButton() {

        ArrayList<Integer> randomNumberList = randomNumberGet(gtbcScore, gtbcTotalScore);

        Button gtbcButton0 = (Button) findViewById(R.id.gtbc_button_0);
        Button gtbcButton1 = (Button) findViewById(R.id.gtbc_button_1);
        Button gtbcButton2 = (Button) findViewById(R.id.gtbc_button_2);
        Button gtbcButton3 = (Button) findViewById(R.id.gtbc_button_3);
        Button gtbcButton4 = (Button) findViewById(R.id.gtbc_button_4);
        Button gtbcButton5 = (Button) findViewById(R.id.gtbc_button_5);

        gtbcButton0.setText(contactArrayList.get(randomNumberList.get(0)).getName());
        gtbcButton1.setText(contactArrayList.get(randomNumberList.get(1)).getName());
        gtbcButton2.setText(contactArrayList.get(randomNumberList.get(2)).getName());
        gtbcButton3.setText(contactArrayList.get(randomNumberList.get(3)).getName());
        gtbcButton4.setText(contactArrayList.get(randomNumberList.get(4)).getName());
        gtbcButton5.setText(contactArrayList.get(randomNumberList.get(5)).getName());

    }


    public void gtbcSettingPicture() {
        ImageView gtbcImageView = (ImageView) findViewById(R.id.gtbc_photo);
        String contactId = contactArrayList.get(gtbcScore).getContactId();
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(contactId));
        InputStream photo_stream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), contactUri, true);
        gtbcImageView.setImageBitmap(BitmapFactory.decodeStream(photo_stream));

//        Uri profilePicture = Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId)), ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
//        gtbcImageView.setImageURI(profilePicture);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fillContactArrayListRandomly();
        gtbcTotalScore = contactArrayList.size();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_to_be_close);
        gtbcStart();
    }


    public void gtbcStart() {
        gtbcSettingButton();
        gtbcSettingPicture();
        gtbcSettingTotalScoreBoard();
    }

}
