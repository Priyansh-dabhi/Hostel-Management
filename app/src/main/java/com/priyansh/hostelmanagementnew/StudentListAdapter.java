package com.priyansh.hostelmanagementnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// StudentListAdapter.java
public class StudentListAdapter extends ArrayAdapter<Student> {
    private Context mContext;
    private List<Student> studentList;
    private MyDatabaseHelper db; // Database helper instance

    public StudentListAdapter(Context context, List<Student> list) {
        super(context, 0, list);
        mContext = context;
        studentList = list;
        db = new MyDatabaseHelper(context); // Initialize the database helper
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_student, parent, false);
        }

        Student student = studentList.get(position);
        // Student in In out Staff
        TextView tvStudentName = convertView.findViewById(R.id.tvStudentName);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        Button btnMarkIn = convertView.findViewById(R.id.btnMarkIn);
        Button btnMarkOut = convertView.findViewById(R.id.btnMarkOut);

        tvStudentName.setText(student.getName());
        tvStatus.setText(student.getStatus());

        // Mark In Button Logic
        btnMarkIn.setOnClickListener(v -> {
            String enrollment = db.getEnrollmentByName(student.getName()); // Fetch enrollment dynamically
            if (enrollment != null) {
                student.setStatus("In");
                tvStatus.setText("In");

                // Get current timestamp
                String timestamp = getCurrentTimestamp();

                // Update the database with "In" status, current timestamp, and enrollment
                db.addInOutTiming(enrollment, "In", timestamp);
            }
        });

        // Mark Out Button Logic
        btnMarkOut.setOnClickListener(v -> {
            String enrollment = db.getEnrollmentByName(student.getName()); // Fetch enrollment dynamically
            if (enrollment != null) {
                student.setStatus("Out");
                tvStatus.setText("Out");

                // Get current timestamp
                String timestamp = getCurrentTimestamp();

                // Update the database with "Out" status, current timestamp, and enrollment
                db.addInOutTiming(enrollment, "Out", timestamp);
            }
        });

        return convertView;
    }

    // Helper method to get current timestamp in a specific format
    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}

