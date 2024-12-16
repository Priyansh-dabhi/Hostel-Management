package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.Calendar;

public class Student_register extends AppCompatActivity {

    private TextView txt;
    public EditText nameEditText, enrollmentEditText,emailEditText, usernameEditText, passwordEditText;
    private Button registerButton;
    MyDatabaseHelper db;  // MY Database


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        txt = findViewById(R.id.greetings4);
        // Set greeting message based on the time of day
        Calendar c = Calendar.getInstance();
        int timeOfTheDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfTheDay >= 0 && timeOfTheDay < 12) {
            txt.setText("Good Morning");
        } else if (timeOfTheDay >= 12 && timeOfTheDay < 16) {
            txt.setText("Good Afternoon");
        } else {
            txt.setText("Good Evening");
        }


        nameEditText = findViewById(R.id.register_name);
        enrollmentEditText = findViewById(R.id.enrollment_no);
        emailEditText = findViewById(R.id.register_email);
        usernameEditText = findViewById(R.id.register_username);
        passwordEditText = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_button);

        db = new MyDatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String enroll = enrollmentEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();


                if (TextUtils.isEmpty(name) ||TextUtils.isEmpty(enroll)|| TextUtils.isEmpty(email) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Student_register.this, "Please enter the details!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(Student_register.this, "Please enter a valid email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!name .isEmpty() &&!enroll.isEmpty() && !email.isEmpty() && !username.isEmpty() && !password.isEmpty()){
                    boolean isInserted = db.registration(name,enroll,email,username,password); // function from MyDatabaseHelper.java
                    if (isInserted){
                        Toast.makeText(Student_register.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Student_register.this,Student_home.class);
                        intent.putExtra("FULLNAME", name);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Student_register.this,"Registration Unsuccessfull",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Student_register.this, "Please enter the details!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
