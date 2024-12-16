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

public class Staff_student_inout extends AppCompatActivity {
    private ListView listViewStudents;
    private StudentListAdapter adapter;
    private List<Student> studentList;
    private MyDatabaseHelper db;
    private String fullName;
    private ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_student_inout);

        back = findViewById(R.id.staff_timing_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Staff_student_inout.this,Staff_home.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize the ListView and Database Helper
        listViewStudents = findViewById(R.id.listViewStudents);
        db = new MyDatabaseHelper(this);

        // Get the username from the Intent
        String name = getIntent().getStringExtra("FULLNAME");

        // Fetch the full name from the database if the name is not null
        if (name != null) {
            fullName = db.getName(name); // Assuming getName returns a String
        }

        // Retrieve the list of students from the database
        studentList = fetchStudentListFromDatabase();

        // Set up the adapter with the retrieved student list
        adapter = new StudentListAdapter(this, studentList);
        listViewStudents.setAdapter(adapter);
    }

    // Method to fetch the list of students from the database
    private List<Student> fetchStudentListFromDatabase() {
        List<Student> students = new ArrayList<>();
        List<String> studentNames = db.getAllStudentNames();

        for (String name : studentNames) {
            students.add(new Student(name, "N/A")); // You can set status as "N/A" or omit it if not required
        }

        return students;
   }


}
