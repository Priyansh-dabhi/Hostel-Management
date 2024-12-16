package com.priyansh.hostelmanagementnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Admin_stafff_management extends AppCompatActivity {
    private ListView listViewstaff;
    private StaffListAdapter adapter;
    private List<Staff> stafflist;
    private MyDatabaseHelper db;
    private ImageView back;
    private ImageView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_stafff_management);

        back =  findViewById(R.id.admin_staff_timing_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_stafff_management.this,Admin_home.class);
                startActivity(intent);
                finish();
            }
        });

        listViewstaff = findViewById(R.id.listViewStaff);
        db = new MyDatabaseHelper(this);

        String name = getIntent().getStringExtra("Username");
        if(name != null){
            String fullname = db.getStaffName(name);
        }

        stafflist = fetchstafflistfromdatabase();

        adapter = new StaffListAdapter(this,stafflist);
        listViewstaff.setAdapter(adapter);

        add =  findViewById(R.id.staff_add_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_stafff_management.this,Admin_insert_staff.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private List<Staff> fetchstafflistfromdatabase(){
        List<Staff> staffs= new ArrayList<>();
        List<String > name = db.getAllStaffNames();
        for (String username : name) {
            staffs.add(new Staff(username));
        }
        return staffs;
    }
}