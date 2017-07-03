package com.project1.cs496_project1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class C extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
    }
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    public void startgame(View view){
        Intent intent = new Intent(this, GetToBeClose.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
