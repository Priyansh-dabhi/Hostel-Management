package com.priyansh.hostelmanagementnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AttendanceAdapter extends ArrayAdapter<Attendance> {
    private Context context;
    private List<Attendance> attendanceList;

    public AttendanceAdapter(Context context, List<Attendance> list) {
        super(context, 0, list);
        this.context = context;
        this.attendanceList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_mess_attendance, parent, false);
        }

        Attendance attendance = attendanceList.get(position);

        TextView tvEnrollment = convertView.findViewById(R.id.messtvEnrollment);
        TextView tvStatus = convertView.findViewById(R.id.messtvStatusStudent);
        TextView tvTimestamp = convertView.findViewById(R.id.messtvTimestamp);

        tvEnrollment.setText(attendance.getEnrollment_in_mess());
        tvStatus.setText(attendance.getStatus_in_mess());
        tvTimestamp.setText(attendance.getTimestamp_in_mess());

        return convertView;
    }
}



