package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Staff_Mess_Attendance extends AppCompatActivity {
    private ListView listViewMessStudents;
    private StudentMessAdapter messAdapter;
    private List<Student_mess> studentList;
    private MyDatabaseHelper db;
    private ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_mess_attendance);

        back = findViewById(R.id.staff_mess_timing_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Staff_Mess_Attendance.this, Staff_home.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize the ListView and Database Helper
        listViewMessStudents = findViewById(R.id.listViewStudents_Mess); // Add this ID in the layout file
        db = new MyDatabaseHelper(this);

        // Retrieve the list of students from the database
        studentList = fetchStudentListFromDatabase();

        // Set up the mess adapter with the retrieved student list
        messAdapter = new StudentMessAdapter(this, studentList);
        listViewMessStudents.setAdapter(messAdapter);
    }

    // Method to fetch the list of students from the database
    private List<Student_mess> fetchStudentListFromDatabase() {
        List<Student_mess> students = new ArrayList<>();
        List<String> studentNames = db.getAllStudentNames();

        for (String name : studentNames) {
            students.add(new Student_mess(name, "N/L")); // You can set the default attendance status here
        }

        return students;
    }
}
