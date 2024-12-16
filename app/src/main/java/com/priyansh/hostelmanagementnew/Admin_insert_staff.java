package com.priyansh.hostelmanagementnew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Admin_insert_staff extends AppCompatActivity {
    private EditText username,password;
    private Button btn;
    MyDatabaseHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_insert_staff);

        username = findViewById(R.id.admin_staff_username);
        password = findViewById(R.id.admin_staff_password);
        btn = findViewById(R.id.admin_staff_btn);
        db = new MyDatabaseHelper(this);
        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String username_staff = username.getText().toString();
                String password_staff = password.getText().toString();
                if ( TextUtils.isEmpty(username_staff) || TextUtils.isEmpty(password_staff)) {
                    Toast.makeText(Admin_insert_staff.this, "Please enter the details!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ( !username_staff.isEmpty() && !password_staff.isEmpty()){
                    boolean isInserted = db.StaffRegistration(username_staff,password_staff); // function from MyDatabaseHelper.java
                    if (isInserted){
                        Toast.makeText(Admin_insert_staff.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Admin_insert_staff.this, Admin_stafff_management.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Admin_insert_staff.this,"Staff Registration Unsuccessfull",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Admin_insert_staff.this, "Please enter the details!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}