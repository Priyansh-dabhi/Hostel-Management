package com.priyansh.hostelmanagementnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {

    private ImageButton imgbtn1,imgbtn2,imgbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        imgbtn1 = findViewById(R.id.imageButtonAdmin);
        imgbtn2 = findViewById(R.id.imageButtonStaff);
        imgbtn3 = findViewById(R.id.imageButtonStudent);

        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Dashboard.this , Admin_login.class);
                startActivity(intent);
                finish();
            }
        }) ;
        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Dashboard.this , Staff_login.class);
                startActivity(intent);
                finish();
            }
        }) ;
        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(Dashboard.this , Student_login.class);
                startActivity(intent);
                finish();
            }
        }) ;
    }
}