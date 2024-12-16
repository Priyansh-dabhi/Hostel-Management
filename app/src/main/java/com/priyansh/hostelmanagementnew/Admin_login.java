package com.priyansh.hostelmanagementnew;

import android.content.Intent;
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

public class Admin_login extends AppCompatActivity {
    private TextView txt;
    private EditText Admin_username,Admin_password;
    private Button Admin_btn;
    private MyDatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);

        txt = findViewById(R.id.greetings3);
        Calendar c = Calendar.getInstance();
        int timeOfTheDay = c.get(Calendar.HOUR_OF_DAY);

        if (timeOfTheDay >= 0 && timeOfTheDay < 12) {
            txt.setText("Good Morning");
        } else if (timeOfTheDay >= 12 && timeOfTheDay < 16) {
            txt.setText("Good Afternoon");
        } else if (timeOfTheDay >= 16 && timeOfTheDay < 24) {
            txt.setText("Good Evening");
        }

        Admin_username = findViewById(R.id.admin_username);
        Admin_password = findViewById(R.id.admin_password);
        Admin_btn = findViewById(R.id.admin_btn);

        // Instance of Database class
        db = new MyDatabaseHelper(this);

        // Validation
        Admin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Admin_username.getText().toString().trim();
                String password = Admin_password.getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    boolean isMatched = db.adminLogin(username, password);
                    if (isMatched) {
                        Toast.makeText(Admin_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Admin_login.this, Admin_home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Admin_login.this, "Invalid credentials! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Admin_login.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}