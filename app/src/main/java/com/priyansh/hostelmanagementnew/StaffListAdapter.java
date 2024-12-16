package com.priyansh.hostelmanagementnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StaffListAdapter extends ArrayAdapter<Staff> {
    private Context mContext;
    private List<Staff> staffList;
    private MyDatabaseHelper db;

    public StaffListAdapter(Context context, List<Staff> list) {
        super(context, 0, list);
        mContext = context;
        staffList = list;
        db = new MyDatabaseHelper(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.admin_list_staff, parent, false);
        }


        Staff staff = staffList.get(position);
        TextView tvStaffName = convertView.findViewById(R.id.admintvStafftName);
        Button btnDelete = convertView.findViewById(R.id.staff_delete);

        tvStaffName.setText(staff.getStaff_username());

        btnDelete.setOnClickListener(v -> {
            String username = staff.getStaff_username();  // Get username from Staff object
            db.deleteStaff(username);                     // Delete staff by username
            staffList.remove(position);                   // Remove item from list
            notifyDataSetChanged();                       // Refresh the ListView
        });

        return convertView;
    }

}
