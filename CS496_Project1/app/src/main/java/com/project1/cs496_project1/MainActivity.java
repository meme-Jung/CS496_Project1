package com.project1.cs496_project1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;       // for permission checking final value

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** override onRequestPermissionResult for permission checking final value
     * if requestCode is MY_PERMISSION_REQUEST_READ_CONTACTS check the grantResults it is granted or not
     * if granted showAddress function call else show text No Permission */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showAddress(getCurrentFocus()); // permission was granted, yay! Do the contacts-related task you need to do.
                }
                else {      // permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(this, "No Permissions ", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 101: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showGallery(getCurrentFocus()); // permission was granted, yay! Do the contacts-related task you need to do.
                }
                else {      // permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(this, "No Permissions ", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 102: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    C(getCurrentFocus()); // permission was granted, yay! Do the contacts-related task you need to do.
                }
                else {      // permission denied, boo! Disable the functionality that depends on this permission.
                    Toast.makeText(this, "No Permissions ", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // other 'case' lines to check for other permissions this app might request
        }
    }

    /** Called when the user clicks the Send button */
    public void showAddress(View view) {
        Intent intent = new Intent(this, DisplayAddressActivity.class);
        // check there is the permission READ_CONTACTS if there is not show pop-up for request Permission, otherwise startActivity showAddress intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            startActivity(intent);
        }
    }


    public void showGallery(View view){
        Intent intent = new Intent(this, getGallery.class);
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
           requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
           //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
       }
       else{
           startActivity(intent);
       }
    }
    public void C(View view){
        Intent intent = new Intent(this, C.class);
        // check there is the permission READ_CONTACTS if there is not show pop-up for request Permission, otherwise startActivity showAddress intent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 102);
        }
        else {
            startActivity(intent);
        }
    }
}
