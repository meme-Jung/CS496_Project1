package com.project1.cs496_project1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;

public class CustomGalleryAdapter extends BaseAdapter {
    int CustomGalleryItemBg;
    Context mContext;
    String mBasePath = null;
    Bitmap bm;
    String[] mImgList;
    public CustomGalleryAdapter(Context c , String basepath) {
        mContext = c;

        this.mBasePath = basepath;

        File file = new File(mBasePath);

        mImgList = file.list();
        TypedArray array = mContext.obtainStyledAttributes(R.styleable.GalleryTheme);
        CustomGalleryItemBg = array.getResourceId(R.styleable.GalleryTheme_android_galleryItemBackground, 0);
        array.recycle();

    }

    @Override
    public int getCount() {
        File dir = new File(mBasePath);
        mImgList = dir.list();
        return mImgList.length;

    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    public String getItemPath(int position){
        String path = mBasePath + File.separator + mImgList[position];
        return path;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
        } else {
            imageView = (ImageView) convertView;
        }
        bm = BitmapFactory.decodeFile(mBasePath + File.separator + mImgList[position]);
        Bitmap mThumbnail = ThumbnailUtils.extractThumbnail(bm, 500, 550);
        imageView.setPadding(8, 8, 8, 8);
        imageView.setRotation(90);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));
        imageView.setImageBitmap(mThumbnail);
        return imageView;
    }
}