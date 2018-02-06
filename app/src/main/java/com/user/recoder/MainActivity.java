package com.user.recoder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final int  MY_PERMISSIONS_REQUEST_CAMERA = 22;
    Button buttonCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCapture = (Button) findViewById(R.id.btn_capture);

        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CAMERA) + ContextCompat
                        .checkSelfPermission(MainActivity.this,
                                Manifest.permission.RECORD_AUDIO)+ ContextCompat
                        .checkSelfPermission(MainActivity.this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)

                        != PackageManager.PERMISSION_GRANTED) {

// Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.RECORD_AUDIO)|| ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {

                        Intent intent = new Intent(MainActivity.this,CaptureActivity.class);
                        startActivity(intent);
// Show an explanation to the user *asynchronously* -- don't block
// this thread waiting for the user's response! After the user
// sees the explanation, try again to request the permission.

                    } else {

// No explanation needed, we can request the permission.

                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_CAMERA);

// app-defined int constant. The callback method gets the
// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
// result of the request.
                    }

                }
                else {
                    Intent intent = new Intent(MainActivity.this,CaptureActivity.class);
                    startActivity(intent);
                }


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
// If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

// permission was granted, yay! Do the
// contacts-related task you need to do.

                } else {

// permission denied, boo! Disable the
// functionality that depends on this permission.
                }
                return;
            }

// other 'case' lines to check for other
// permissions this app might request.
        }
    }

}
