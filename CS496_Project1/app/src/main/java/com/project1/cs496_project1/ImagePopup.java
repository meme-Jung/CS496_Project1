package com.project1.cs496_project1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.project1.cs496_project1.R;


public class ImagePopup extends AppCompatActivity{
    private Context mContext = null;
    private final int imgWidth = 320;
    private final int imgHeight = 372;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_popup);
        mContext = this;

        /** 전송메시지 */
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String name = extras.getString("name");

        /** 완성된 이미지 보여주기  */
        ImageView iv = (ImageView)findViewById(R.id.imageview);
        Bitmap bm = BitmapFactory.decodeFile(name);
        iv.setPadding(8, 8, 8, 8);
        iv.setRotation(90);
        iv.setImageBitmap(bm);
    }
}
