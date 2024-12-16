package com.priyansh.hostelmanagementnew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InOutTimingAdapter extends ArrayAdapter<InOutTiming> {

    public InOutTimingAdapter(Context context, List<InOutTiming> timings) {
        super(context, 0, timings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_in_out_timing, parent, false);
        }

        InOutTiming timing = getItem(position);

        TextView enrollment = convertView.findViewById(R.id.tvEnrollment);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        TextView tvTimestamp = convertView.findViewById(R.id.tvTimestamp);

        enrollment.setText(timing.getEnrollment());
        tvStatus.setText(timing.getStatus());
        tvTimestamp.setText(timing.getTimestamp());

        return convertView;
    }
}
