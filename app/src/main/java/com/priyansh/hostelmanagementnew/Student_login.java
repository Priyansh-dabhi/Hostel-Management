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
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.Calendar;
//
//public class Student_login extends AppCompatActivity {
//    private TextView txt;
//    private TextView link;
//    private EditText loginEnrollment, loginPassword;
//    private Button loginButton;
//    private MyDatabaseHelper db;
//
//    private static final String PREFS_NAME = "UserPrefs";
//    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
//    private static final String KEY_ENROLLMENT = "enrollment";
//
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_login);
//        // save login state
//
//
//
//
//        txt = findViewById(R.id.greetings3);
//
//        // Display greeting message based on the time of day
//        Calendar c = Calendar.getInstance();
//        int timeOfTheDay = c.get(Calendar.HOUR_OF_DAY);
//        if (timeOfTheDay >= 0 && timeOfTheDay < 12) {
//            txt.setText("Good Morning");
//        } else if (timeOfTheDay >= 12 && timeOfTheDay < 16) {
//            txt.setText("Good Afternoon");
//        } else {
//            txt.setText("Good Evening");
//        }
//
//        link = findViewById(R.id.registerText);
//        loginEnrollment = findViewById(R.id.login_enrollment);
//        loginPassword = findViewById(R.id.login_password);
//        loginButton = findViewById(R.id.button);
//
//        // Redirect to registration page if not registered
//        link.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Student_login.this, Student_register.class);
//                startActivity(intent);
//            }
//        });
//
//        // Creating instance of database
//        db = new MyDatabaseHelper(this);
//
//        // Set up login button functionality
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String enrollment = loginEnrollment.getText().toString().trim();
//                String password = loginPassword.getText().toString().trim();
//
//                if (!enrollment.isEmpty() && !password.isEmpty()) {
//                    boolean isMatched = db.loginvalidation(enrollment, password);
//                    if (isMatched) {
//                        Intent intent = new Intent(Student_login.this, Student_home.class);
//                        startActivity(intent);
//                        finish();
//                        Toast.makeText(Student_login.this, "Login Successfull.", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(Student_login.this, "Invalid credentials! Please try again.", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Student_login.this, "Please enter both enrollment and password", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//}
//
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Student_login extends AppCompatActivity {
    private TextView txt;
    private TextView link;
    private EditText loginEnrollment, loginPassword;
    private Button loginButton;
    private MyDatabaseHelper db;

    private static final String PREFS_NAME = "UserPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_ENROLLMENT = "enrollment";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        // Check if user is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);

        if (isLoggedIn) {

            Intent intent = new Intent(Student_login.this, Student_home.class);
            startActivity(intent);
            finish();
            return;
        }

        txt = findViewById(R.id.greetings3);

        // Display greeting message based on the time of day
        Calendar c = Calendar.getInstance();
        int timeOfTheDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfTheDay >= 0 && timeOfTheDay < 12) {
            txt.setText("Good Morning");
        } else if (timeOfTheDay >= 12 && timeOfTheDay < 16) {
            txt.setText("Good Afternoon");
        } else {
            txt.setText("Good Evening");
        }

        link = findViewById(R.id.registerText);
        loginEnrollment = findViewById(R.id.login_enrollment);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.button);

        // Redirect to registration page if not registered
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Student_login.this, Student_register.class);
                startActivity(intent);
            }
        });

        // Creating instance of database
        db = new MyDatabaseHelper(this);

        // Set up login button functionality
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enrollment = loginEnrollment.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (!enrollment.isEmpty() && !password.isEmpty()) {
                    boolean isMatched = db.loginvalidation(enrollment, password);
                    if (isMatched) {
                        // save login state and enrollment to sharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(KEY_IS_LOGGED_IN, true);
                        editor.putString(KEY_ENROLLMENT, enrollment);
                        editor.apply();

                        // Redirect to home screen
                        Intent intent = new Intent(Student_login.this, Student_home.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Student_login.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Student_login.this, "Invalid credentials! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Student_login.this, "Please enter both enrollment and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
