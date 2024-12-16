package com.priyansh.hostelmanagementnew;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Student_profile extends AppCompatActivity {
    private TextView txt;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        // Initialize MyDatabaseHelper
        db = new MyDatabaseHelper(this);

        txt = findViewById(R.id.profile_name);

        // Get full name from Intent
        String fullName = getIntent().getStringExtra("FULLNAME");

        if (fullName != null) {
            txt.setText(fullName);
        } else {
            txt.setText("No Name Found");
        }
    }
}
