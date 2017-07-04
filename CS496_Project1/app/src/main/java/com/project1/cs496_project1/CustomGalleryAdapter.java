package com.project1.cs496_project1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CustomGalleryAdapter extends BaseAdapter {
    int CustomGalleryItemBg;
    Context mContext;
    String mBasePath = null;

    String mBasePath2 = null;
    Bitmap bm = null;
    String[] mImgList1;
    String[] mImgList2;
    String[] mImgList;
    private LruCache mMemoryCache;

    public CustomGalleryAdapter(Context c , String basepath, String basepath2) {
        mContext = c;

        this.mBasePath = basepath;
        this.mBasePath2 = basepath2;

        File file = new File(mBasePath);
        File file2 = new File(mBasePath2);
        mImgList1 = file.list();
        mImgList2 = file2.list();
        int i;
        final int memClass = ((ActivityManager) mContext.getSystemService(
                Context.ACTIVITY_SERVICE)).getMemoryClass();
        final int cacheSize = 1024 * 1024 * memClass/2;
        mMemoryCache = new LruCache(cacheSize){

            protected int sizeOf(String Key, Bitmap bitmap){
                return (bitmap.getRowBytes() * bitmap.getHeight() * 4)/1024;
            }
        };
        int count=0;
        mImgList = new String[mImgList1.length+mImgList2.length];
        for (i=0; i<mImgList2.length; i++)
        {
            if(mImgList2[i].contains("jpg")) {
                mImgList2[i] = mBasePath2 + File.separator + mImgList2[i];
                mImgList[count] = mImgList2[i];
                count ++;
            }
        }
        for (i=0; i<mImgList1.length; i++)
        {
            if(mImgList1[i].contains("jpg")) {
                mImgList1[i] = mBasePath + File.separator + mImgList1[i];
                mImgList[count] = mImgList1[i];
                count++;
            }
        }
        count --;
        TypedArray array = mContext.obtainStyledAttributes(R.styleable.GalleryTheme);
        CustomGalleryItemBg = array.getResourceId(R.styleable.GalleryTheme_android_galleryItemBackground, 0);
        array.recycle();
    }

    @Override
    public int getCount() {
        return mImgList.length;
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return (Bitmap) mMemoryCache.get(key);
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Log.i("getView", String.valueOf(position));
        String imageKey = String.valueOf(position);
        Bitmap bm = getBitmapFromMemCache(imageKey);
        imageView = new ImageView(mContext);
        if (bm == null) {
            bm = BitmapFactory.decodeFile(mImgList[position]);
            Bitmap mThumbnail = ThumbnailUtils.extractThumbnail(bm, 500, 550);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT));

            if (mThumbnail != null) {
                if(mImgList[position].contains(mBasePath)) {
                }
                addBitmapToMemoryCache(imageKey, mThumbnail);
            }
            imageView.setImageBitmap(mThumbnail);
        }
        else {
            if(mImgList[position].contains(mBasePath)) {
                imageView.setRotation(90);
            }
            imageView.setImageBitmap(bm);
        }
        return imageView;
    }
}