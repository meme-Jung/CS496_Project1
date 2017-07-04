package com.project1.cs496_project1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOverPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_page);
    }
    public void gotomain(View view){
        finish();
    }
    public void retry(View view){
        Intent intent = new Intent(this, GetToBeClose.class);
        startActivity(intent);
        finish();
    }
}
