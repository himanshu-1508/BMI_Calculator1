package com.himanshusingh.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDial,btnCall,btnMessage,btnEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnDial = findViewById(R.id.btn_Dail);
        btnCall = findViewById(R.id.btn_Call);
        btnMessage = findViewById(R.id.btn_Message);
        btnEmail = findViewById(R.id.btn_Email);

        btnDial.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_Dail)
        {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:8287422376"));
            startActivity(dialIntent);
        }

        if(view.getId()==R.id.btn_Call)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
            {
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8287422376"));
                startActivity(dialIntent);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},101);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101)
        {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:8287422376"));
                startActivity(dialIntent);
            }
            else
            {

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}