package com.priyansh.hostelmanagementnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class student_inAnd_out extends AppCompatActivity {

    private ListView listView;
    private InOutTimingAdapter adapter;
    private MyDatabaseHelper databaseHelper;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_in_and_out);

        listView = findViewById(R.id.listViewTimings);
        databaseHelper = new MyDatabaseHelper(this);

        // Fetch data from the database
        List<InOutTiming> inOutTimings = databaseHelper.getAllInOutData();

        // Initialize and set up the adapter
        adapter = new InOutTimingAdapter(this, inOutTimings);
        listView.setAdapter(adapter);

        back = findViewById(R.id.student_timing_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(student_inAnd_out.this,Student_home.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

////        listViewTimings = findViewById(R.id.listViewTimings);
////        db = new MyDatabaseHelper(this);
////
////        // Assuming username is passed in the intent
////        String username_inout = getIntent().getStringExtra("USERNAME");
////
////        if (username_inout != null) {
////            Log.d(TAG, "Username: " + username_inout);
////
////            // Fetch In/Out timings for this student
////            timingsList = db.getInOutTimingsForStudent(username_inout);
////
////            if (timingsList != null) {
////                adapter = new InOutTimingAdapter(this, timingsList);
////                listViewTimings.setAdapter(adapter);
////            } else {
////                Log.e(TAG, "timingsList is null");
////                Toast.makeText(this, "No timings found for user.", Toast.LENGTH_SHORT).show();
////            }
////        } else {
////            Log.e(TAG, "Username is null");
////            Toast.makeText(this, "Username is not passed properly.", Toast.LENGTH_SHORT).show();
////        }

//package com.priyansh.hostelmanagementnew;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import java.util.List;
//
//public class student_inAnd_out extends AppCompatActivity {
//
//    private ListView listView;
//    private InOutTimingAdapter adapter;
//    private MyDatabaseHelper databaseHelper;
//    private ImageView back;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_in_and_out);
//
//        listView = findViewById(R.id.listViewTimings);
//        databaseHelper = new MyDatabaseHelper(this);
//
//        // Retrieve enrollment from SharedPreferences
//        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
//        String enrollment = sharedPreferences.getString("ENROLLMENT", null);
//
//        if (enrollment != null) {
//            // Fetch In/Out timings for this student based on enrollment
//            List<InOutTiming> inOutTimings = databaseHelper.getAllInOutData(enrollment);
//
//            if (inOutTimings != null && !inOutTimings.isEmpty()) {
//                // Initialize and set up the adapter
//                adapter = new InOutTimingAdapter(this, inOutTimings);
//                listView.setAdapter(adapter);
//            } else {
//                Toast.makeText(this, "No timings found for this user.", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Log.e("StudentInOut", "Enrollment is null");
//            Toast.makeText(this, "Enrollment not found. Please log in again.", Toast.LENGTH_SHORT).show();
//        }
//
//        back = findViewById(R.id.student_timing_back_btn);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(student_inAnd_out.this, Student_home.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
