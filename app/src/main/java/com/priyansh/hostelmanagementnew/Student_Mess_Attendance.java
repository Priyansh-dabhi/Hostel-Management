package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class Student_Mess_Attendance extends AppCompatActivity {
    private ListView listView;
    private AttendanceAdapter adapter;
    private MyDatabaseHelper db;
    private ImageView back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_mess_attendance);

        listView = findViewById(R.id.listViewAttendance); // Ensure ID matches your XML layout
        db = new MyDatabaseHelper(this);

        // Fetch data from the database with error handling
        List<Attendance> attendance;
        try {
            attendance = db.getAllAttendanceData();
        } catch (Exception e) {
            e.printStackTrace();
            attendance = null;
        }

        if (attendance != null) {
            // Initialize and set up the adapter
            adapter = new AttendanceAdapter(this, attendance);
            listView.setAdapter(adapter);
        }
        back = findViewById(R.id.student_mess_timing_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_Mess_Attendance.this,Student_home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}