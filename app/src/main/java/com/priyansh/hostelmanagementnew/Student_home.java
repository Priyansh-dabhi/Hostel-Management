//package com.priyansh.hostelmanagementnew;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.PopupMenu;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class Student_home extends AppCompatActivity {
//    ImageView menu_btn;
//    Button inout_btn, messAttendance_btn;
//    MyDatabaseHelper db;
//    String fullName;
//    String username;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_student_home);
//
//        db = new MyDatabaseHelper(this);
//
//        // Get Fullname from Intent
//        String name = getIntent().getStringExtra("FULLNAME");
//
//        if (name != null) {
//            // Fetch full name from the database
//            fullName = db.getName(name);
//        }
//
//        menu_btn = findViewById(R.id.student_menu_icon);
//        menu_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu menu = new PopupMenu(Student_home.this, menu_btn);
//                menu.getMenuInflater().inflate(R.menu.student_option_menu, menu.getMenu());
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        int item_id = menuItem.getItemId();
//
//                        if (item_id == R.id.student_profile) {
//                            Intent intent = new Intent(Student_home.this, Student_profile.class);
//                            intent.putExtra("FULLNAME", fullName); // Pass the full name for profile module
//                            startActivity(intent);
//                            finish();
//                        } else if (item_id == R.id.hostel_rules) {
//                            Intent intent = new Intent(Student_home.this, Hostel_Rules.class);
//                            startActivity(intent);
//                            finish();
//                        } else if (item_id == R.id.help_support) {
//                            Intent intent = new Intent(Student_home.this, HelpAndSupport.class);
//                            startActivity(intent);
//                            finish();
//                        } else if (item_id == R.id.student_logout) {
//                            Intent intent = new Intent(Student_home.this, Dashboard.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Toast.makeText(Student_home.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//                menu.show();
//            }
//        });
//
////        // Get Username from Intent
////        String username_inout = getIntent().getStringExtra("USERNAME");
////
////        if (name != null) {
////            // Fetch full name from the database
////            username = db.getUsername(username_inout);
////        }
////
//
//        inout_btn = findViewById(R.id.student_inoutBtn);
//        messAttendance_btn = findViewById(R.id.student_mess_btn);
//
//        inout_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Student_home.this, student_inAnd_out.class);
////                intent.putExtra("USERNAME", username_inout);
//                startActivity(intent);
//                finish();
//            }
//        });
//        messAttendance_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Student_home.this, Student_Mess_Attendance.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Student_home extends AppCompatActivity {
    private ImageView menu_btn;
    private Button inout_btn, messAttendance_btn;
    private MyDatabaseHelper db;
    private String fullName;

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        db = new MyDatabaseHelper(this);

        // Get Fullname from Intent
        String name = getIntent().getStringExtra("FULLNAME");

        if (name != null) {
            // Fetch full name from the database
            fullName = db.getName(name);
        }

        // Initialize menu button
        menu_btn = findViewById(R.id.student_menu_icon);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(Student_home.this, menu_btn);
                menu.getMenuInflater().inflate(R.menu.student_option_menu, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();

                        if (itemId == R.id.student_profile) {
                            openStudentProfile();
                        } else if (itemId == R.id.hostel_rules) {
                            startActivity(new Intent(Student_home.this, Hostel_Rules.class));
                        } else if (itemId == R.id.help_support) {
                            startActivity(new Intent(Student_home.this, HelpAndSupport.class));
                        } else if (itemId == R.id.student_logout) {
                            logoutUser();
                        }

                        Toast.makeText(Student_home.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                menu.show();
            }
        });

        // Initialize buttons and set click listeners
        inout_btn = findViewById(R.id.student_inoutBtn);
        messAttendance_btn = findViewById(R.id.student_mess_btn);

        inout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInOutActivity();
            }
        });

        messAttendance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Student_home.this, Student_Mess_Attendance.class));
            }
        });
    }

    private void openStudentProfile() {
        Intent intent = new Intent(Student_home.this, Student_profile.class);
        intent.putExtra("FULLNAME", fullName); // Pass the full name for profile module
        startActivity(intent);
    }

    private void openInOutActivity() {
        Intent intent = new Intent(Student_home.this, student_inAnd_out.class);
        startActivity(intent);
    }

    private void logoutUser() {
        // Clear login state in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to Dashboard or Login screen
        Intent intent = new Intent(Student_home.this, Dashboard.class);
        startActivity(intent);
        finish();
    }
}
