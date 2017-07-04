package com.project1.cs496_project1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
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
    private ImageView resultView;
    public CustomGalleryAdapter mCustomGalleryAdapter;
    public String basePath2 = null;
    public GridView mGridView2;
    public CustomGalleryAdapter mCustomGalleryAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_gallery);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File downloadDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "");
        if (!downloadDir.exists()) {
            if (!downloadDir.mkdirs()) {
                Log.d("Camera", "failed to create directory");
            }
        }
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("Camera", "failed to create directory");
            }
        }
        basePath = mediaStorageDir.getPath();
        basePath2 = downloadDir.getPath();
        mGridView = (GridView) findViewById(R.id.gridview); // .xml의 GridView와 연결
        mCustomGalleryAdapter = new CustomGalleryAdapter(this, basePath, basePath2); // 앞에서 정의한 Custom Image Adapter와 연결

        final String[] mImgList = mCustomGalleryAdapter.mImgList;
        mGridView.setAdapter(mCustomGalleryAdapter); // GridView 출력

        final Intent intent = new Intent(this, ImagePopup.class);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("name", mImgList[position]);
                startActivity(intent);
            }
        });
    }
}
