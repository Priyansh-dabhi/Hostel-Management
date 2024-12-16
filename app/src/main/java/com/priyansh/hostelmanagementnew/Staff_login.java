//package com.priyansh.hostelmanagementnew;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import java.util.Calendar;
//
//public class Staff_login extends AppCompatActivity {
//    private TextView txt;
//    private EditText Staff_username,Staff_password;
//    private Button Staff_btn;
//    private MyDatabaseHelper db;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_staff_login);
//
//        txt = findViewById(R.id.greetings2);
//        Calendar c = Calendar.getInstance();
//        int timeOfTheDay = c.get(Calendar.HOUR_OF_DAY);
//
//        if (timeOfTheDay >= 0 && timeOfTheDay < 12) {
//            txt.setText("Good Morning");
//        } else if (timeOfTheDay >= 12 && timeOfTheDay < 16) {
//            txt.setText("Good Afternoon");
//        } else if (timeOfTheDay >= 16 && timeOfTheDay < 24) {
//            txt.setText("Good Evening");
//        }
//
//        Staff_username = findViewById(R.id.staff_username);
//        Staff_password = findViewById(R.id.staff_password);
//        Staff_btn = findViewById(R.id.staff_btn);
//
//        // Instance of Database class
//        db = new MyDatabaseHelper(this);
//
//        // Validation
//        Staff_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = Staff_username.getText().toString();
//                String password = Staff_password.getText().toString();
//                if (!username.isEmpty() && !password.isEmpty()) {
//                    boolean isMatched = db.staffLogin(username, password);
//                    if (isMatched) {
//                        Toast.makeText(Staff_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(Staff_login.this, Staff_home.class);
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        Toast.makeText(Staff_login.this, "Invalid credentials! Please try again.", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Staff_login.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class Staff_login extends AppCompatActivity {
    private TextView txt;
    private EditText Staff_username,Staff_password;
    private Button Staff_btn;
    private MyDatabaseHelper db;
    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USERNAME = "enrollment";



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staff_login);

        // Check if staff is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);

        if (isLoggedIn) {

            Intent intent = new Intent(Staff_login.this, Staff_home.class);
            startActivity(intent);
            finish();
            return;
        }

        txt = findViewById(R.id.greetings2);
        Calendar c = Calendar.getInstance();
        int timeOfTheDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfTheDay >= 0 && timeOfTheDay < 12) {
            txt.setText("Good Morning");
        } else if (timeOfTheDay >= 12 && timeOfTheDay < 16) {
            txt.setText("Good Afternoon");
        } else if (timeOfTheDay >= 16 && timeOfTheDay < 24) {
            txt.setText("Good Evening");
        }

        Staff_username = findViewById(R.id.staff_username);
        Staff_password = findViewById(R.id.staff_password);
        Staff_btn = findViewById(R.id.staff_btn);

        // Instance of Database class
        db = new MyDatabaseHelper(this);

        // Validation
        Staff_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Staff_username.getText().toString();
                String password = Staff_password.getText().toString();
                if (!username.isEmpty() && !password.isEmpty()) {
                    boolean isMatched = db.staffLogin(username, password);
                    if (isMatched) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(KEY_IS_LOGGED_IN,true);
                        editor.putString(KEY_USERNAME,username);
                        editor.apply();


                        Intent intent = new Intent(Staff_login.this, Staff_home.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Staff_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Staff_login.this, "Invalid credentials! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Staff_login.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}