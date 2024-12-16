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

public class StudentMessAdapter extends ArrayAdapter<Student_mess> {
    private Context mContext;
    private List<Student_mess> studentMessList;
    private MyDatabaseHelper db; // Database helper instance

    public StudentMessAdapter(Context context, List<Student_mess> list) {
        super(context, 0, list);
        mContext = context;
        studentMessList = list;
        db = new MyDatabaseHelper(context); // Initialize the database helper
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.mess_list_student, parent, false);
        }

        Student_mess student = studentMessList.get(position);

        // Mess Attendance UI
        TextView tvStudentName = convertView.findViewById(R.id.messtvStudentName);
        TextView tvStatus = convertView.findViewById(R.id.messtvStatus);
        Button btnPresent = convertView.findViewById(R.id.messPresentbtn);
        Button btnAbsent = convertView.findViewById(R.id.messAbsentbtn);

        tvStudentName.setText(student.getMessName());
        tvStatus.setText("N/L");

        // Present Button Logic
        btnPresent.setOnClickListener(v -> {
            String enrollment = db.getEnrollmentByName(student.getMessName());
            if (enrollment != null) {
                tvStatus.setText("Present");

                String timestamp = getCurrentTimestamp();
                db.addAttendance(enrollment, "Present", timestamp);
            }
        });

        // Absent Button Logic
        btnAbsent.setOnClickListener(v -> {
            String enrollment = db.getEnrollmentByName(student.getMessName());
            if (enrollment != null) {
                tvStatus.setText("Absent");

                String timestamp = getCurrentTimestamp();
                db.addAttendance(enrollment, "Absent", timestamp);
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
