package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Phonebook(View view) {
        Intent intent = new Intent(this, ContactListActivity.class);
        startActivity(intent);
    }
    public void Gallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }
}
