package com.project1.cs496_project1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getGallery extends AppCompatActivity {
    public String basePath = null;
    public GridView mGridView;
    public CustomGalleryAdapter mCustomGalleryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_gallery);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("Camera", "failed to create directory");
            }
        }

        basePath = mediaStorageDir.getPath();

        mGridView = (GridView)findViewById(R.id.gridview); // .xml의 GridView와 연결
        mCustomGalleryAdapter = new CustomGalleryAdapter(this, basePath); // 앞에서 정의한 Custom Image Adapter와 연결
        mGridView.setAdapter(mCustomGalleryAdapter); // GridView가 Custom Image Adapter에서 받은 값을 뿌릴 수 있도록 연결
    }
}
