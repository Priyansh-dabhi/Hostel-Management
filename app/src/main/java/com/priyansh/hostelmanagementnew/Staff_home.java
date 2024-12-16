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
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class Staff_home extends AppCompatActivity {
//    ImageView menu_btn;
//    Button inout_btn, messAttendance_btn;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_staff_home);
//
//        menu_btn = findViewById(R.id.staff_menu_icon);
//        menu_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                PopupMenu menu = new PopupMenu(Staff_home.this, menu_btn);
//                menu.getMenuInflater().inflate(R.menu.staff_option_menu, menu.getMenu());
//                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem menuItem) {
//                        int item_id = menuItem.getItemId();
//
//                        if (item_id == R.id.staff_profile) {
//                            Intent intent = new Intent(Staff_home.this, Staff_profile.class);
//                            startActivity(intent);
//                            finish();
//                        } else if (item_id == R.id.hostel_rules) {
//                            Intent intent = new Intent(Staff_home.this, Hostel_Rules.class);
//                            startActivity(intent);
//                            finish();
//                        } else if (item_id == R.id.help_support) {
//                            Intent intent = new Intent(Staff_home.this, HelpAndSupport.class);
//                            startActivity(intent);
//                            finish();
//                        } else if (item_id == R.id.staff_logout) {
//                            Intent intent = new Intent(Staff_home.this, Dashboard.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        Toast.makeText(Staff_home.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//                menu.show();
//            }
//        });
//
//        inout_btn = findViewById(R.id.staff_inoutBtn);
//        messAttendance_btn = findViewById(R.id.staff_mess_btn);
//
//        inout_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Staff_home.this, Staff_student_inout.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        messAttendance_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Staff_home.this, Staff_Mess_Attendance.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Staff_home extends AppCompatActivity {
    ImageView menu_btn;
    Button inout_btn, messAttendance_btn;
    private static final String PREFS_NAME = "UserPrefs";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staff_home);

        menu_btn = findViewById(R.id.staff_menu_icon);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(Staff_home.this, menu_btn);
                menu.getMenuInflater().inflate(R.menu.staff_option_menu, menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int item_id = menuItem.getItemId();

                        if (item_id == R.id.staff_profile) {
                            Intent intent = new Intent(Staff_home.this, Staff_profile.class);
                            startActivity(intent);
                            finish();
                        } else if (item_id == R.id.hostel_rules) {
                            Intent intent = new Intent(Staff_home.this, Hostel_Rules.class);
                            startActivity(intent);
                            finish();
                        } else if (item_id == R.id.help_support) {
                            Intent intent = new Intent(Staff_home.this, HelpAndSupport.class);
                            startActivity(intent);
                            finish();
                        } else if (item_id == R.id.staff_logout) {
                            logoutUser(); // Clear shared preferences and log out
                        }
                        Toast.makeText(Staff_home.this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                menu.show();
            }
        });

        inout_btn = findViewById(R.id.staff_inoutBtn);
        messAttendance_btn = findViewById(R.id.staff_mess_btn);

        inout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Staff_home.this, Staff_student_inout.class);
                startActivity(intent);
                finish();
            }
        });
        messAttendance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Staff_home.this, Staff_Mess_Attendance.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void logoutUser() {
        // Clear the shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirect to the Dashboard
        Intent intent = new Intent(Staff_home.this, Dashboard.class);
        startActivity(intent);
        finish(); // Close Staff_home activity
    }
}
