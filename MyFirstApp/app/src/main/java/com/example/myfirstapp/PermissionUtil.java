package com.example.myfirstapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

/**
 * Created by q on 2017-06-30.
 */

public class PermissionUtil {/*
    public static int checkSelfPremission(@NonNull Context context, @NonNull String permission)
    {
        if(permission == null){
            throw new IllegalArgumentException("permission is null");
        }
        return  context.checkPermission(permission, android.os.Process.myPid(), Process.myUid());
    }
    public static boolean checkPremissions(Activity activity, String permission){
        int permissionResult = ActivityCompat.checkSelfPermission(activity,permission);
        if (permissionResult == PackageManager.PERMISSION_GRANTED) return true;
        else return false;
    }*/

    public static void requestPermissions(final @NonNull Activity activity, final @NonNull String[] permissions, final int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        } else if (activity instanceof ActivityCompat.OnRequestPermissionsResultCallback) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Thread() {
                public void run () {
                    final int[] grantResults = new int[permissions.length];
                    PackageManager packageManager = activity.getPackageManager();
                    String packageName = activity.getPackageName();
                    final int permissionCount = permissions.length;
                    for (int i = 0; i < permissionCount; i++) {
                        grantResults[i] = packageManager.checkPermission(permissions[i], packageName);
                    }
                    ((ActivityCompat.OnRequestPermissionsResultCallback) activity).onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            });
        }
    }

    private static final int REQUEST_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void requestExternalPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_STORAGE);
    }

    public static boolean verifyPermission(int[] grantresults) {
        if (grantresults.length < 1) {
            return false;
        }
        for (int result : grantresults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
