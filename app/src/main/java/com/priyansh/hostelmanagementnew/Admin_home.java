package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Admin_home extends AppCompatActivity {
    private Button staff;
    private Button Student;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        staff = findViewById(R.id.admin_staff_btn);
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home.this, Admin_stafff_management.class);
                startActivity(intent);
                finish();
                Toast.makeText(Admin_home.this,"Staff list",Toast.LENGTH_SHORT).show();
            }
        });
        Student  = findViewById(R.id.admin_StudentBtn);
        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_home.this, Student_home.class);
                startActivity(intent);
                finish();
                Toast.makeText(Admin_home.this,"Student Home",Toast.LENGTH_SHORT).show();
            }
        });
    }
}